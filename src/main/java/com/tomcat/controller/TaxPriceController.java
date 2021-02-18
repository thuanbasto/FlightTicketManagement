package com.tomcat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tomcat.service.ITaxPriceService;

@Controller

public class TaxPriceController {
	@Autowired
	ITaxPriceService taxpriceService;
	@RequestMapping("/admin")
	public ModelAndView homePage(HttpServletRequest request) {
		request.setAttribute("listTaxPrice", taxpriceService.getList());
		ModelAndView mav = new ModelAndView("ListTax");
		
		return mav;
	}

}
