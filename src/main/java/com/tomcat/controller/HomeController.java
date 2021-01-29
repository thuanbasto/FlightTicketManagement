package com.tomcat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tomcat.service.ICityService;

@Controller
public class HomeController {
	@Autowired
	ICityService cityService;
	
	@GetMapping(value= {"/home","/"})
	public String homePage(HttpServletRequest request) {
		request.setAttribute("listCity", cityService.getList());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName());
		System.out.println(auth.getCredentials());
		System.out.println(auth.getAuthorities().size());
		System.out.println(auth.getAuthorities());
		
		return "Home";
	}
	
	@GetMapping("/signin")
	public String signPage() {
		return "Signin";
	}
}
