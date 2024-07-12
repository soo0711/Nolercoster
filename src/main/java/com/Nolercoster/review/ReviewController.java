package com.Nolercoster.review;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/review")
public class ReviewController {

	@GetMapping("/review-detail-view")
	public String detailView(Model model) {
		model.addAttribute("viewName", "/review/detail");
		return "template/layout";
	}
}
