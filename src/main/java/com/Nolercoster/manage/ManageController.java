package com.Nolercoster.manage;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Nolercoster.manage.bo.ManageBO;
import com.Nolercoster.qna.entity.QnaEntity;
import com.Nolercoster.qnaCard.domain.QnaCard;
import com.Nolercoster.user.entity.UserEntity;


@Controller
@RequestMapping("/manage")
public class ManageController {
	
	@Autowired
	private ManageBO manageBO;

	@GetMapping("/qna-list-view")
	public String qnaListView(Model model) {
		List<QnaCard> qnaCardList = manageBO.getQnaCardList(); 
		
		model.addAttribute("viewName", "manage/qnaList");
		model.addAttribute("qnaCardList", qnaCardList);
		
		return "template/layout";
	}
	
	@GetMapping("/list-view")
	public String listView(
			@RequestParam("menu") int menu
			, Model model) {
		
		if (menu == 1) { // qna
			List<QnaCard> qnaCardList = manageBO.getQnaCardList(); 
			model.addAttribute("qnaCardList", qnaCardList);
			
			return "manage/qnaList";
		} 
		
		if (menu == 2) { // save
			return "template/layout";
		}
		
		if (menu == 3) { // user
			List<UserEntity> userList = manageBO.getUserList();
			model.addAttribute("userList", userList);
			
			return "manage/userList";
		}
		return "template/layout";
	}
	
	@GetMapping("/find-user-by-name")
	public String findUserByName(
			@RequestParam("name") String name
			, Model model) {
		
		
		List<UserEntity> userList = manageBO.getUserListByName(name);
		model.addAttribute("userList", userList);
		
		return "manage/userList";
	}
}
