package com.Nolercoster.qna.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Nolercoster.qna.entity.QnaEntity;
import com.Nolercoster.qna.repository.QnaRepository;

@Service
public class QnaBO {
	
	@Autowired
	private QnaRepository qnaRepository;

	// input: X 		output: List<QnaEntity>
	public List<QnaEntity> getQnaList(){
		return qnaRepository.findAll();
	}
}
