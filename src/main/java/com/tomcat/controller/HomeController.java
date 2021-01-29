package com.tomcat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
		return "home";
	}
	
	@GetMapping(value= {"/booking"})
	public String bookingPage(HttpServletRequest request) {
		request.setAttribute("listCity", cityService.getList());
		return "booking";
	}
}
