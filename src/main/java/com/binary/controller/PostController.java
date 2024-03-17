package com.binary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/auth")
public class PostController {

	@GetMapping("/post")
	public String post(Model model) {
		
		model.addAttribute("title", "Show Post");
		return "features/posts/show_posts";
	}
}
