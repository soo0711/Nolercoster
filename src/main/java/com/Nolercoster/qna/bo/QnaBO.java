package com.Nolercoster.qna.bo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Nolercoster.qna.entity.QnaEntity;
import com.Nolercoster.qna.repository.QnaRepository;
import com.Nolercoster.user.entity.UserEntity;

@Service
public class QnaBO {
	
	@Autowired
	private QnaRepository qnaRepository;

	// input: X 		output: List<QnaEntity>
	public List<QnaEntity> getQnaList(){
		return qnaRepository.findAll();
	}
	
	public Integer addQna(int userId, String subject, String context) {
		QnaEntity qnaEntity = qnaRepository.save(
				QnaEntity.builder()
				.userId(userId)
				.subject(subject)
				.context(context)
				.build()
				);
		return qnaEntity == null? null : qnaEntity.getId();
				
	}
	
	public QnaEntity getQnaEntityById(int qnaId) {
		return qnaRepository.findById(qnaId).orElse(null);
	}

	public void updateReply(int qnaId, String reply) {
		QnaEntity qnaEntity = qnaRepository.findById(qnaId).orElse(null);
		qnaEntity = qnaEntity.toBuilder() // 기존 내용은 그대로
                .reply(reply)
                .updatedAt(LocalDateTime.now())
                .build();
		qnaRepository.save(qnaEntity); // 데이터 있으면 수정
	}
	
	
	
}
