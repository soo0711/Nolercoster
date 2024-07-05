package com.Nolercoster.manage.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Nolercoster.qnaCard.bo.QnaCardBO;
import com.Nolercoster.qnaCard.domain.QnaCard;
import com.Nolercoster.user.bo.UserBO;
import com.Nolercoster.user.entity.UserEntity;

@Service
public class ManageBO {

	@Autowired
	private UserBO userBO;
	
	@Autowired
	private QnaCardBO qnaCardBO;
	
	// input: X 		output: List<UserEntity>
	public List<UserEntity> getUserList(){
		return userBO.getUserList();
	}
	
	
	// input: name 		output: List<UserEntity>
	public List<UserEntity> getUserListByName(String name){
		return userBO.getUserListByName(name);
	}
	
	// input: X 		output: List<QnaEntity>
	public List<QnaCard> getQnaCardList(){
		return qnaCardBO.getQnaCardList();
	}
	
	
	
}
