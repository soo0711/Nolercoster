package com.Nolercoster.user.entity;

import java.util.Date;

import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "user")
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "loginId")
	private String loginId;
	
	private String password;
	
	private String name;
	
	private String email;
	
	@Column(name = "userToken")
	private String userToken;
	
	@Column(name = "phoneNumber")
	private String phoneNumber;
	
	@Column(name = "nickName")
	private String nickName;
	
	private String route;
	
	private int coin;
	
	@Column(name = "createdAt", updatable = false)
	@UpdateTimestamp
	private Date createdAt;
	
	@Column(name = "updatedAt")
	@UpdateTimestamp
	private Date updatedAt;
}
