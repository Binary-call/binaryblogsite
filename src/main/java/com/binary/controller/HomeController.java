package com.binary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/auth")
public class HomeController {
	
	@GetMapping("/home")
	public String index(Model model) {
		
		model.addAttribute("title", "Home");
		return "index";
	}
}
