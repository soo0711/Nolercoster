package com.Nolercoster.qnaCard.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Nolercoster.qna.bo.QnaBO;
import com.Nolercoster.qna.entity.QnaEntity;
import com.Nolercoster.qnaCard.domain.QnaCard;
import com.Nolercoster.user.bo.UserBO;
import com.Nolercoster.user.entity.UserEntity;

@Service
public class QnaCardBO {
	
	@Autowired
	private QnaBO qnaBO;
	
	@Autowired
	private UserBO userBO;

	// input: X 		output: List<QnaCard>
	public List<QnaCard> getQnaCardList(){
		
		List<QnaCard> qnaCardList = new ArrayList<>();
		
		// qna 목록 가져오기
		List<QnaEntity> qnaList = qnaBO.getQnaList();
		
		for (QnaEntity qna : qnaList) {
			QnaCard qnaCard = new QnaCard();
			
			// qna set
			qnaCard.setQna(qna);
			
			// user set
			UserEntity user = userBO.getUserEntityById(qna.getUserId());
			qnaCard.setUser(user);
			
			qnaCardList.add(qnaCard);
		}
		
		return qnaCardList;
	}
	
	// input: qnaId		output: QnaCard
	public QnaCard getQnaCardById(int qnaId) {
		QnaCard qnaCard = new QnaCard();
		
		QnaEntity qna = qnaBO.getQnaEntityById(qnaId);
		qnaCard.setQna(qna);
		
		UserEntity uesr = userBO.getUserEntityById(qna.getUserId());
		qnaCard.setUser(uesr);
		
		return qnaCard;
	}
}
