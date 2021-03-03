package com.tomcat.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tomcat.dto.RoleDTO;
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
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
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
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		userService.save(userDTO);
		return "redirect:/admin/user-management?signup=success";
	}
	
	@GetMapping(value= {"/dashboard","/"})
	public String adminPage() {
		return "Dashboard";
	}
	
	@GetMapping(value= {"/city-management"})
	public String cityManagement() {
		return "City";
	}
	
	@GetMapping(value= {"/airport-management"})
	public String airportManagement() {
		return "Airport";
	}
	
	@GetMapping(value= {"/airplane-management"})
	public String airplaneManagement(HttpServletRequest request) {
		return "Airplane";
	}
	
	@GetMapping(value= {"/tax-management"})
	public String taxManagement() {
		return "Tax";
	}
	
	@GetMapping(value= {"/user-management"})
	public String userManagement(HttpServletRequest request) {
		Set<RoleDTO> roles = roleService.getRoleDTOList();
		request.setAttribute("roles", roles);
		return "User";
	}
	
	@GetMapping(value= {"/customer-management"})
	public String customerManagement() {
		return "Customer";
	}
	
	@GetMapping(value= {"/seat-management"})
	public String seatManagement() {
		return "Seat";
	}
	
	@GetMapping(value= {"/travelclass-management"})
	public String travelClassManagement() {
		return "TravelClass";
	}
		
	@GetMapping(value= {"/flight-management"})
	public String flightManagement() {
		return "Flight";
	}
	
	@GetMapping(value= {"/luggage-management"})
	public String luggageManagement() {
		return "Luggage";
	}
	
	@GetMapping(value= {"/revenue"})
	public String revenue() {
		return "Revenue";
	}
}
