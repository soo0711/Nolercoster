package com.Nolercoster.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Nolercoster.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
