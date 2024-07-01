package com.Nolercoster.qna.entity;

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
@Table(name ="qna")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class QnaEntity {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		@Column(name = "userId")
		int userId;
		
		String subject;
		
		String context;
		
		String reply;
		
		@Column(name = "createdAt", updatable = false)
		@UpdateTimestamp
		Date createdAt;
		
		@Column(name = "updatedAt")
		@UpdateTimestamp
		Date updatedAt;
}
