package com.Nolercoster;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nolercoster")
public class NolercosterController {

	@GetMapping("/home-view")
	public String homeView(Model model) {
		model.addAttribute("viewName", "menu/home");
		return "template/layout";
	}

}
