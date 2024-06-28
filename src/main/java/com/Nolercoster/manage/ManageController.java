package com.Nolercoster.manage;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/manage")
public class ManageController {

	@GetMapping("/qna-list-view")
	public String qnaListView(Model model) {
		model.addAttribute("viewName", "manage/qnaList");
		return "template/layout";
	}
	
}
