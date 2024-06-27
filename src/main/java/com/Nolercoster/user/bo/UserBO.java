package com.Nolercoster.user.bo;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Nolercoster.user.entity.UserEntity;
import com.Nolercoster.user.repository.UserRepository;

@Service
public class UserBO {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserPrivateBO userPrivateBO;
	
	// input: params	output: Integer (Id pk) - 회원가입 
	public Integer addUser(String loginId, String hasedPassword, String name, String phoneNumber, String email) {
		UserEntity userEntity = userRepository.save(
				UserEntity.builder()
				.loginId(loginId)
				.password(hasedPassword)
				.name(name)
				.nickName(name)
				.phoneNumber(phoneNumber)
				.email(email)
				.coin(10)
				.route("mainPage")
				.build()
				);
		return userEntity == null? null : userEntity.getId(); 
	}
	
	// input: userId, salt 		output: X - 비밀번호 난수 저장
	public void addUserPrivate(int userId, String salt) {
		userPrivateBO.addUserPrivate(userId, salt);
	}
	
	
	public int isDuplicatedId(String loginId) {
		UserEntity userEntity = userRepository.findByLoginId(loginId);
		if(userEntity != null) {
			return 1;
		} else {
			return 0;
		}
		
	}
	
	public UserEntity findByUserToken(String userToken) {
		return userRepository.findByUserToken(userToken);
	}
	
	public UserEntity insertUserByKakao (String userToken, String userName, String signUpFlag, String userProfileImage, String loginProvider) {
		UserEntity user = UserEntity.builder()
				.userToken(userToken)
				.userName(userName)
				.createdAt(ZonedDateTime.now())
				.build();
		
		return userRepository.save(user);
	}
	
	
	// input: loginId		output: UserEntity
	public UserEntity getUserEntityByLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}
	
	// input: userId		output: salt
	public String getUserPrivateByUserId(int userId) {
		return userPrivateBO.getUserPrivateByUserId(userId);
	}
	
	// input: loginId, pw		output: UserEntity
	public UserEntity getUserEntityByLoginIdAndPassword(String loginId, String password) {
		return userRepository.findByLoginIdAndPassword(loginId, password);
	}
}
