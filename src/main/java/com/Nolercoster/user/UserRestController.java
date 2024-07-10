package com.Nolercoster.user;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Nolercoster.certification.bo.CertificationBO;
import com.Nolercoster.certification.entity.CertificationEntity;
import com.Nolercoster.common.EncryptUtils;
import com.Nolercoster.user.bo.UserBO;
import com.Nolercoster.user.entity.UserEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private EncryptUtils encryptUtils;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CertificationBO certificationBO;
	
	

	@PostMapping("/sign-up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("email") String email) throws NoSuchAlgorithmException{
		
		// 비밀번호 암호화 SHA-256 알고리즘
		// salt(난수) 저장
		String salt = encryptUtils.createSalt(password);
		
		// 해시값 저장
		String hasedPassword = encryptUtils.SHA256(password, salt);
		
		// user db insert
		Integer userId = userBO.addUser(loginId, hasedPassword, name, phoneNumber, email);
		
		// userPrivate db insert - salt
		userBO.addUserPrivate(userId, salt);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
	
	
	@PostMapping("/is-duplicated-id")
	public Map<String, Object> isDuplicatedId (
			@RequestParam("loginId") String loginId
			) {
		
		Map<String, Object> result = new HashMap<>();
		int count = userBO.isDuplicatedId(loginId);
		if(count > 0) {
			result.put("code", 200);
			result.put("is_duplicated_id", true);
		} else {
			result.put("code", 200);
			result.put("is_duplicated_id", false);
		}
		
		return result;
		
	}

	
	
	@PostMapping("/sign-up-kakao")
	public Map<String, Object> signUpKakap(
			@RequestParam("name") String name,
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("email") String email,
			HttpSession session) throws NoSuchAlgorithmException{
		
		String userToken = (String) session.getAttribute("userToken");
		String loginProvider = (String) session.getAttribute("login_provider");
		String nickName = (String) session.getAttribute("nickname");
		
		// user db insert
		Integer userId = userBO.addUserKako(userToken, loginProvider, nickName, name, phoneNumber, email);
		
		
		Map<String, Object> result = new HashMap<>();
		if(userId != null) {
			session.setAttribute("userId", userId);
			result.put("code", 200);
			result.put("result", "성공");
		}
		
		return result;
	}
	
	@PostMapping("/sign-in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request){
		
		Map<String, Object> result = new HashMap<>();
		
		// id 유무
		UserEntity user = userBO.getUserEntityByLoginId(loginId);
		if (user == null) {
			result.put("code", 500);
			result.put("error_message", "로그인에 실패했습니다.");
			return result;
		}
		
		// hasedPassword 만들기
		// user의 salt 가져오기
		String salt = userBO.getUserPrivateByUserId(user.getId());	
		// hasedPassword로 로그인
		password = encryptUtils.SHA256(password, salt);
		
		// loginId - pw 맞는지 확인
		user = userBO.getUserEntityByLoginIdAndPassword(loginId, password);
		
		if (user != null) {
			// 세션 추가
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());
			
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "로그인에 실패했습니다.");
		}
		
		return result;
	}
	
	@PostMapping("/update")
	public Map<String, Object> update(
			@RequestParam("password") String password,
			@RequestParam("nickName") String nickName,
			@RequestParam("phoneNumber") String phoneNumber,
			HttpSession session){
		
		// userId
		Integer userId = (Integer)session.getAttribute("userId");
		
		// update BO
		userBO.updateUserInfo(userId, password, nickName, phoneNumber);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		return result;
		
	}
	
	/**
	 * 비밀번호 찾기 API
	 * @param loginId
	 * @param email
	 * @param session
	 * @return
	 */
	@PostMapping("/find-pw")
	public Map<String, Object> findPW (
			@RequestParam("loginId") String loginId,
			@RequestParam("email") String email,
			HttpSession session) {
		//이름과 이메일이 일치하는 사용자가 있는지 확인
		UserEntity user = userBO.getUserEntityByLoginIdAndEmail(loginId, email);
		Map<String, Object> result = new HashMap<>();
		if(user != null) {
			if(user.getPassword() == null) {
				result.put("code", 500);
				result.put("error_message", "카카로 로그인 회원의 비밀번호 찾기는 카카오를 통해 문의주세요");
			} else {
				userBO.sendEmail(user);
				session.setAttribute("userId", user.getId());
				result.put("code", 200);
				result.put("result", "성공");
			}
		} else {
			result.put("code", 500);
			result.put("error_message", "이름과 이메일이 일치하는 회원을 찾지 못 했습니다 정보를 다시 입력해주세요");
		}
		
		return result;
	}
	
	@PostMapping("/check-certificationCode")
	public Map<String, Object> checkCertificationCode(
			@RequestParam("code") String code,
			HttpSession session) {
		Integer userId = (Integer)session.getAttribute("userId");
		CertificationEntity certification =certificationBO.getCertificationByCertificationCode(userId, code);
		Map<String, Object> result = new HashMap<>();
		if(certification != null) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "인증번호가 일치히지 않습니다.");
		}
		
		certificationBO.deleteCertification(userId, code);
		
		return result;
		
	}
	
	/**
	 * 아이디 찾기 API
	 * @param name
	 * @param email
	 * @return
	 */
	@PostMapping("/find-id")
	public Map<String, Object> findId(
			@RequestParam("name") String name,
			@RequestParam("email") String email){
	
		// user select
		UserEntity user = userBO.getUserEntityByNameAndEmail(name, email);
		
		Map<String, Object> result = new HashMap<>();
	
		if(user != null) {
			result.put("code", 200);
			result.put("loginId", user.getLoginId());
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "가입되지 않은 회원입니다.");
		}
		
		return result;
	}
}
