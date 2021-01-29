package com.tomcat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/signup")
	public String signupPage() {
//		userValidator.validate(userDTO, bindingResult);
//		if (bindingResult.hasErrors()) {
//			return "signUp";
//		}
//		userService.addUser(userDTO);
		return "redirect:/home?signup=success";
	}
}
