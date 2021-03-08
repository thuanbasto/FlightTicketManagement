package com.tomcat.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tomcat.dto.CityDTO;
import com.tomcat.service.ICityService;
import com.tomcat.service.IFlightService;
import com.tomcat.service.IRoleService;
import com.tomcat.service.IUserService;

@Controller
public class HomeController {
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IRoleService roleService;
	
	@Autowired
	ICityService cityService;
	
	@Autowired
	private IFlightService flightService;
	
	@GetMapping(value= {"/home","/"})
	public String homePage(HttpServletRequest request) {
		request.setAttribute("listCity", cityService.getList());
		
		return "Home";
	}
	
	@GetMapping("/signin")
	public String signInPage(HttpServletRequest request,
			@RequestParam(name="error" ,required=false) String error) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean anonymous = authentication.getAuthorities().toString().equals("[ROLE_ANONYMOUS]");

		// not logged in + no error -> return Signin page
		if (anonymous && error == null)
			return "Signin";
		// not logged in + error 
		if (anonymous && error != null) 
			if (error.equals("failed")) // login unsuccessful 
				request.setAttribute("msg", "Username or password is incorret.");
			else 
				return "Deny"; // 403 : Forbidden Error -> return deny page
		// logged in + no error 
		if (!anonymous && error != null)
			if (error.equals("failed")) // can't log in again when logging in
				return "redirect:/home";
			else 
				return "Deny"; // 403 : Forbidden Error -> return deny page
		// logged in + error -> return home page
		if (!anonymous && error == null)
			return "redirect:/home";
		
		return "Signin";
	}
	
	@GetMapping(value= {"/book"})
	public String bookingPage(HttpServletRequest request) {
		Set<CityDTO> citis = cityService.getCityDTOList();
		request.setAttribute("citis", citis);
		return "Book";
	}
	
	@GetMapping(value= {"/accountadmin"})
	public String generateAccountAdmin(HttpServletRequest request) {
		if(userService.generateAccountAdmin()) request.setAttribute("generated", 1);
		else request.setAttribute("generated", 0);
		return "GenerateAccountAdmin";
	}
	
	@GetMapping(value= {"/searchFlight"})
	public String searchFlight(HttpServletRequest request,
			@RequestParam(name="from",required=true) String from,
			@RequestParam(name="to",required=true) String to,
			@RequestParam(name="departureDate",required=true) @DateTimeFormat(pattern="yyyy-MM-dd") Date departureDate,
			@RequestParam(name="number",required=true) String number) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(departureDate);
        
		String url = "from=" + from + "&to=" + to + "&departureDate=" + strDate + "&number=" + number;
		request.setAttribute("url", url);
		return "ResultFlight";
	}
}
