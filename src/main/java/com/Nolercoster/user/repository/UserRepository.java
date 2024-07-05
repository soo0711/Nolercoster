package com.Nolercoster.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Nolercoster.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	public UserEntity findByLoginId(String loginId);
	
	public UserEntity findByLoginIdAndPassword(String loginId, String password);
	
	public UserEntity findByUserToken(String userToken);
	
	public UserEntity findById(int userId);
	
	
	public List<UserEntity> findByName(String name);
}
