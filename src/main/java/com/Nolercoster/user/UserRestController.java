package com.Nolercoster.user;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Nolercoster.common.EncryptUtils;
import com.Nolercoster.user.bo.UserBO;

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
	
}
