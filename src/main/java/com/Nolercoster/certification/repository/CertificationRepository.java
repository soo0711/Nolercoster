package com.Nolercoster.certification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Nolercoster.certification.entity.CertificationEntity;
import com.Nolercoster.user.entity.UserEntity;

public interface CertificationRepository extends JpaRepository<CertificationEntity, Integer>{

	public CertificationEntity findByCertificationCodeAndUserId(String code, int userId);
	public CertificationEntity deleteByCertificationCodeAndUserId(String code, int userId);
}
