package com.Nolercoster.qnaCard.domain;

import com.Nolercoster.qna.entity.QnaEntity;
import com.Nolercoster.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@Data // getter, setter
@ToString
public class QnaCard {
	
	// 유저 정보
	private UserEntity user;
	
	// 그 유저의 qna 정보
	private QnaEntity qna;

}
