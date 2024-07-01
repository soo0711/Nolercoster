package com.Nolercoster.manage.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Nolercoster.qna.bo.QnaBO;
import com.Nolercoster.qna.entity.QnaEntity;
import com.Nolercoster.user.bo.UserBO;
import com.Nolercoster.user.entity.UserEntity;

@Service
public class ManageBO {

	@Autowired
	private UserBO userBO;
	
	@Autowired
	private QnaBO qnaBO;
	
	// input: X 		output: List<UserEntity>
	public List<UserEntity> getUserList(){
		return userBO.getUserList();
	}
	
	// input: X 		output: List<QnaEntity>
	public List<QnaEntity> getQnaList(){
		return qnaBO.getQnaList();
	}
}
