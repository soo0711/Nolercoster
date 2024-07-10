package com.Nolercoster.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Nolercoster.user.bo.UserBO;
import com.Nolercoster.user.entity.UserEntity;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserBO userBO;
	
	@Value("${kakao.ApiKey}")
	private String kakaoApiKey;
	
	@Value("${kakao.RedirectUri}")
	private String kakaoRedirectUri;
	
	@Value("${kakao.kakaoLogoutRedirectUri}")
	private String kakaoLogoutRedirectUri;
	
	/**
	 * 회원가입 화면
	 * @param model
	 * @return
	 */
	@GetMapping("/sign-up-view")
	public String signUpView(Model model) {
		model.addAttribute("viewName", "/user/signUp");

		return "template/layout";
	}
	
	/**
	 * 로그인 화면
	 * @param model
	 * @return
	 */
	@GetMapping("/sign-in-view")
	public String signInView(Model model) {
		model.addAttribute("viewName", "/user/signIn");
		model.addAttribute("kakaoApiKey", kakaoApiKey);
		model.addAttribute("redirectUri", kakaoRedirectUri);
		
		return "template/layout";
	}
	
	
	/**
	 * 카카오 로그인시 추가 정보 입력 화면(회원가입)
	 * @param model
	 * @return
	 */
	@GetMapping("/sign-in-kakao-view")
	public String signInKakaoView(Model model) {
		model.addAttribute("viewName", "/user/insertDetail");
		
		return "template/layout";
	}
	
	/**
	 * 마이페이지 내정보 수정 화면
	 * @param model
	 * @return
	 */
	@GetMapping("/update-view")
	public String updateView(
			HttpSession session,
			Model model) {
		
		int userId = (int) session.getAttribute("userId");
		
		// db select - user
		UserEntity user = userBO.getUserEntityById(userId);
		
		model.addAttribute("viewName", "/user/update");
		model.addAttribute("user", user);
		model.addAttribute("start", user.getPhoneNumber().substring(0, 3));
		model.addAttribute("middle", user.getPhoneNumber().substring(3, 7));
		model.addAttribute("end", user.getPhoneNumber().substring(7));
		
		return "template/layout";
	}
	
	/**
	 * 비밀번호 찾기 화면
	 * @param model
	 * @return
	 */
	@GetMapping("/find-password-view")
	public String findPasswordView(Model model) {
		model.addAttribute("viewName", "/user/findPW");
		
		return "template/layout";
	}
	
	/**
	 * 인증 번호 입력화면
	 * @param model
	 * @return
	 */
	@GetMapping("/check-certificationCode-view")
	public String checkCertificationCodeView (Model model) {
		model.addAttribute("viewName", "/user/checkCertificationCode");
		return "template/layout";
	}
	
	@GetMapping("/reset-password-view")
	public String resetPasswordView (Model model) {
		model.addAttribute("viewName", "/user/resetPassword");
		return "template/layout"; 
	}
	
	/**
	 * 아이디 찾기 화면
	 * @param model
	 * @return
	 */
	@GetMapping("/find-id-view")
	public String findIdView(Model model) {
		model.addAttribute("viewName", "/user/findId");
		
		return "template/layout";
	}
}
