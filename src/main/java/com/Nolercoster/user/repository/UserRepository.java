package com.Nolercoster.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Nolercoster.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	public UserEntity findByLoginId(String loginId);
	
	public UserEntity findByLoginIdAndPassword(String loginId, String password);
	
//	public UserEntity findByUserToken(String userToken);
}
