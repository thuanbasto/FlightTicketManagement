package com.tomcat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tomcat.dto.UserDTO;
import com.tomcat.service.IUserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	IUserService userService;
	
	@GetMapping("/signup")
	public String signupPage(@ModelAttribute("userDTO") UserDTO userDTO) {
		return "Signup";
	}
	
	@PostMapping("/signup")
	public String signupPageA(@ModelAttribute("userDTO") UserDTO userDTO) {
//		userValidator.validate(userDTO, bindingResult);
//		if (bindingResult.hasErrors()) {
//			return "signUp";
//		}
		userService.add(userDTO);
		return "redirect:/home?signup=success";
	}
	
	@GetMapping(value= {"/dashboard"})
	public String adminPage(HttpServletRequest request) {
//		request.setAttribute("listCity", cityService.getList());
		return "Dashboard";
	}
	
	@GetMapping(value= {"/city-management"})
	public String cityManagement(HttpServletRequest request) {
//		request.setAttribute("listCity", cityService.getList());
		return "City";
	}
	@GetMapping(value= {"/tax-management"})
	public String taxManagement(HttpServletRequest request) {
//		request.setAttribute("listCity", cityService.getList());
		return "Tax";
	}
	
}
