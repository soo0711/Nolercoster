package com.Nolercoster.certification.entity;

import java.util.Date;

import org.hibernate.annotations.UpdateTimestamp;

import com.Nolercoster.user.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@Table(name = "certification")
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CertificationEntity {
    
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "userId")
    private int userId;
    
    @Column(name = "certificationCode")
    private String certificationCode;
    
    @Column(name = "createdAt", updatable = false)
	@UpdateTimestamp
    private Date createdAt;
	
	
}
