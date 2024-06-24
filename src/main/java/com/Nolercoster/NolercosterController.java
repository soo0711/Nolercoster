package com.Nolercoster;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nolercoster")
public class NolercosterController {

	@RequestMapping("/home-view")
	public String homeView(Model model) {
		model.addAttribute("viewName", "home/homeView");
		return "home/home";
	}
}
