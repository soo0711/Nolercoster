package com.Nolercoster.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Nolercoster.user.entity.UserPrivateEntity;
import com.Nolercoster.user.repository.UserPrivateRepository;

@Service
public class UserPrivateBO {
	
	@Autowired
	private UserPrivateRepository userPrivateRepository;

	public void addUserPrivate(int userId, String salt) {
		UserPrivateEntity userPrivateEntity = userPrivateRepository.save(
				UserPrivateEntity.builder()
				.userId(userId)
				.salt(salt)
				.build()
				);
	}
}
