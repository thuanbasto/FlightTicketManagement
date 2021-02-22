package com.tomcat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tomcat.dto.UserDTO;
import com.tomcat.service.IRoleService;
import com.tomcat.service.ITaxService;
import com.tomcat.service.IUserService;
import com.tomcat.validator.UserValidator;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	IRoleService roleService;
	
	@Autowired
	IUserService userService;

	@Autowired
	UserValidator userValidator;
	
	@Autowired
	ITaxService taxService;
	
	
	@GetMapping("/signup")
	public String signupPage(@ModelAttribute("userDTO") UserDTO userDTO) {
		userDTO.setRoles(roleService.getRoleDTOList());
		return "Signup";
	}
	
	@PostMapping("/signup")
	public String signupPageA(@ModelAttribute("userDTO") UserDTO userDTO,BindingResult bindingResult) {
		userValidator.validate(userDTO, bindingResult);
		if (bindingResult.hasErrors()) {
			userDTO.setRoles(roleService.getRoleDTOList());
			return "Signup";
		}
		userService.add(userDTO);
		return "redirect:/home?signup=success";
	}
	
	@GetMapping(value= {"/dashboard","/"})
	public String adminPage(HttpServletRequest request) {
		return "Dashboard";
	}
	
	@GetMapping(value= {"/city-management"})
	public String cityManagement(HttpServletRequest request) {
		return "City";
	}
	
	@GetMapping(value= {"/airport-management"})
	public String airportManagement(HttpServletRequest request) {
		return "Airport";
	}
	
	@GetMapping(value= {"/tax-management"})
	public String taxManagement(HttpServletRequest request) {
		return "Tax";
	}
}
