package com.binary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class LoginController {

	@GetMapping("/login")
	public String getLogin(Model model) {
		
		model.addAttribute("title", "Login Page");
		return "login";
	}
	
	@GetMapping("/register")
	public String getRegister(Model model) {
		
		model.addAttribute("title", "Register Page");
		return "register";
	}
}
