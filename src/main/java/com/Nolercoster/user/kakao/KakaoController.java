package com.Nolercoster.user.kakao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Nolercoster.user.bo.UserBO;
import com.Nolercoster.user.entity.UserEntity;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class KakaoController {
	@Autowired
	private UserBO userBO;
	
	private KakaoApi kakaoApi = new KakaoApi();

	@Value("${kakao.ApiKey}")
	private String kakaoApiKey;
	
	@Value("${kakao.RedirectUri}")
	private String kakaoRedirectUri;
	
	@Value("${kakao.kakaoLogoutRedirectUri}")
	private String kakaoLogoutRedirectUri;
	
	@GetMapping("/user/kakaoLogin")
	public String kakaoLoginView(Model model) {
		model.addAttribute("kakaoApiKey", kakaoApiKey);
		model.addAttribute("redirectUri", kakaoRedirectUri);

		return "user/KakaoLogin";
	}

	
	@GetMapping("/login/oauth/code/kakao")
	public String kakaoLogin(@RequestParam("code") String code,
			Model model,
			HttpSession session) {
		// 1. 인가 코드 받기(@RequestParam String code)

		// 2. 토큰받기
		String accessToken = kakaoApi.getAccessToken(code);

		
		
		// 3사용자 정보 받기
		Map<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);
		//String profileImage = (String) userInfo.get("profileImage");
		String nickname = (String) userInfo.get("nickname");
		String userToken = (String) userInfo.get("userToken");

        //세선에 회원 정보 저장
        session.setAttribute("kakaoToken", accessToken); //로그아웃을 위해 토큰 session에 저장 필요
        session.setAttribute("nickname", nickname);
        session.setAttribute("userToken", userToken); //회원가입을 위해 user 고유 id 저장 필요
        //session.setAttribute("profileImage", profileImage); 

		// 3- 1 userId값이 user table 에 없을 때
		UserEntity user = userBO.findByUserToken(userToken);
		if (user == null) { // 해당 id를 가진 user가 없다면 회원가입 절차
			session.setAttribute("login_provider", "kakao"); 
			
			return "user/insertDetail";
		}

		
		//profileImage = user.getUserProfileImage();
		nickname = user.getName();
        //model.addAttribute("profile_image", profileImage);
        model.addAttribute("nickname", nickname);
        
		
        
        //로그아웃을 위한 restApikey, redirect URl
        model.addAttribute("kakaoApiKey", kakaoApiKey);
		model.addAttribute("logoutRedirectUri", kakaoLogoutRedirectUri);
		return "user/insertDetail";
	}


	
	@GetMapping("/logout/oauth/code/kakao")
	public String kakaoLogout(HttpSession session) {
		String accessToken = (String) session.getAttribute("kakaoToken");
		
		if(accessToken != null) {
			kakaoApi.kakaoLogout(accessToken);
			session.removeAttribute("kakaoToken");
			session.removeAttribute("nickname");
			System.out.println("로그아웃 완료");
		}
		
		return "redirect:/user/kakaoLogin";
	}
	
	

	@GetMapping("/user/signUp")
	public void signUp(
			@RequestParam("userName")String userName, 
			@RequestParam("signUpFlag")String signUpFlag,
			HttpSession session) {
		String userToken = (String) session.getAttribute("userToken");
		//String profileImage = (String) session.getAttribute("profileImage");
		String loginProvider = (String) session.getAttribute("login_provider");
		
		//insert user
		//userBO.insertUser(userToken, userName, signUpFlag, profileImage, loginProvider);
	
	}
	
	
}

