package com.Nolercoster.certification.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Nolercoster.certification.entity.CertificationEntity;
import com.Nolercoster.certification.repository.CertificationRepository;

@Service
public class CertificationBO {


	
	@Autowired
	private CertificationRepository certificationRepository;
	
	
	public CertificationEntity addCertification(int userId, String certificationCode) {
		CertificationEntity certificationEntity = certificationRepository.save(
				CertificationEntity.builder()
				.userId(userId)
				.certificationCode(certificationCode)
				.build()
				);
	
		return certificationEntity;
	}
	
	public CertificationEntity getCertificationByCertificationCode(int userId, String code) {
		CertificationEntity certification = certificationRepository.findByCertificationCodeAndUserId(code, userId);
		
		return certification;
	}
	
	public void deleteCertification(int userId, String code) {
		certificationRepository.deleteByCertificationCodeAndUserId(code, userId);
	}
	
	
}
