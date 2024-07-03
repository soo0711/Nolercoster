package com.Nolercoster.user;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
		
		// userPrivate db insert - salt
		//userBO.addUserPrivate(userId, salt);
		
		Map<String, Object> result = new HashMap<>();
		if(userId != null) {
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
	
}
