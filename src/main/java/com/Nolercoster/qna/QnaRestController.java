package com.Nolercoster.qna;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Nolercoster.qna.bo.QnaBO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/qna")
public class QnaRestController {
	
	@Autowired
	private QnaBO qnaBO;

	@PostMapping("/qna-create")
	public Map<String, Object> qnaCreate(
			@RequestParam("subject") String subject,
			@RequestParam("context") String context,
			HttpSession session) {
		Integer userId = (Integer)session.getAttribute("userId");
		Integer qnaId = qnaBO.addQna(userId, subject, context);
		
		
		Map<String, Object> result = new HashMap<>();
		if(qnaId != null) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "문의글 등록에 실패했습니다. 관리자에게 문의하세요.");
		}
		
		return result;
	}
	
	@PostMapping("/qna-reply")
	public Map<String, Object> qnaReply(
			@RequestParam("qnaId") int qnaId,
			@RequestParam("reply") String reply,
			HttpSession session) {
		qnaBO.updateReply(qnaId, reply);
		
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
}
