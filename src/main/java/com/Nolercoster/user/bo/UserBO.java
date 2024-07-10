package com.Nolercoster.user.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Nolercoster.certification.bo.CertificationBO;
import com.Nolercoster.certification.bo.MailBO;
import com.Nolercoster.certification.domain.Mail;
import com.Nolercoster.common.EncryptUtils;
import com.Nolercoster.user.entity.UserEntity;
import com.Nolercoster.user.repository.UserRepository;

@Service
public class UserBO {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserPrivateBO userPrivateBO;
	
	@Autowired
	private EncryptUtils encryptUtils;
	
	@Autowired
	private MailBO mailBO;
	
	@Autowired 
	private CertificationBO certificationBO;
	
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
	
	// input: X			output: List<UserEntity>
	public List<UserEntity> getUserList(){
		return userRepository.findAll();
	}
	
	// input: X			output: UserEntity
	public UserEntity getUserEntityById(int userId){
		return userRepository.findById(userId);
	}
	
	public Integer addUserKako(String userToken, String loginProvider, String nickName, String name, String phoneNumber, String email) {
		UserEntity userEntity = userRepository.save(
				UserEntity.builder()
				.userToken(userToken)
				.name(name)
				.nickName(nickName)
				.phoneNumber(phoneNumber)
				.email(email)
				.coin(10)
				.route(loginProvider)
				.build()
				);
		return userEntity == null? null : userEntity.getId(); 
	}

	// input: paramas 		output: void
	public void updateUserInfo(int userId, String password, String nickName, String phoneNumber) {
		// 비밀번호 있을시에
		if (password != "") {
			String userSalt = userPrivateBO.getUserPrivateByUserId(userId);
			String hasedPassword = encryptUtils.SHA256(password, userSalt);
			updatePassword(userId, hasedPassword);
		}
		// 닉네임 있을시에
		if (nickName != "") {
			updateNickname(userId, nickName);
		}
		// 전화번호 있을시에
		if (phoneNumber != "") {
			updatephoneNumber(userId, phoneNumber);
		}
	}
	
	// input: userId, password		output: X
	public void updatePassword(int userId, String password) {
		UserEntity user = userRepository.findById(userId);
		if(user != null) {
			user = user.toBuilder()
					.password(password)
					.build();
			
			userRepository.save(user); // 데이터 있으면 수정
		}
	}
	
	// input: nickname	output: X
	public void updateNickname(int userId, String nickName) {
		UserEntity user = userRepository.findById(userId);
		if(user != null) {
			user = user.toBuilder()
					.nickName(nickName)
					.build();
			
			userRepository.save(user); // 데이터 있으면 수정
		}
	}
	
	// input: nickname	output: X
	public void updatephoneNumber(int userId, String phoneNumber) {
		UserEntity user = userRepository.findById(userId);
		if(user != null) {
			user = user.toBuilder()
					.phoneNumber(phoneNumber)
					.build();
			
			userRepository.save(user); // 데이터 있으면 수정
		}
	}
	
	public List<UserEntity> getUserListByName(String name) {
		return userRepository.findByName(name);
	}
	
	public UserEntity getUserEntityByLoginIdAndEmail(String loginId, String email) {
		return userRepository.findByLoginIdAndEmail(loginId, email);
	}
	
	
	public void sendEmail(UserEntity user) {
		String cetificationCode = mailBO.getCertificationCode();
		Mail mail = mailBO.createMailAndChangePassword(user.getEmail(), cetificationCode);
		mailBO.mailSend(mail);
		certificationBO.addCertification(user.getId(), cetificationCode);
		
	}
	
	public UserEntity getUserEntityByNameAndEmail(String name, String email) {
		return userRepository.findByNameAndEmail(name, email);
	}
	
	public void resetPassword(int userId, String password) {
		String userSalt = userPrivateBO.getUserPrivateByUserId(userId);
		String hasedPassword = encryptUtils.SHA256(password, userSalt);
		updatePassword(userId, hasedPassword);
	}
}
