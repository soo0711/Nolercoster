package com.Nolercoster.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Nolercoster.qnaCard.bo.QnaCardBO;
import com.Nolercoster.qnaCard.domain.QnaCard;

@RequestMapping("/qna")
@Controller
public class QnaController {

	@Autowired
	private QnaCardBO qnaCardBO;
	
	/**
	 * qna 리스트
	 * @param model
	 * @return
	 */
	@GetMapping("/qna-list-view")
	public String qnaListView(Model model) {
		model.addAttribute("viewName", "/qna/list");
		List<QnaCard> qnaCardList = qnaCardBO.getQnaCardList(); 
		model.addAttribute("qnaCardList", qnaCardList);
		return "template/layout";
	}
	
	/**
	 * qna 작성
	 * @param model
	 * @return
	 */
	@GetMapping("/qna-create-view")
	public String qnaCreateView(Model model) {
		model.addAttribute("viewName", "/qna/create");
		return "template/layout";
	}
	
}
