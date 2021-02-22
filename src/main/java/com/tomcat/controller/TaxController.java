package com.tomcat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.protobuf.Method;
import com.tomcat.dto.TaxPriceDTO;
import com.tomcat.service.ITaxPriceService;
import com.tomcat.service.ITaxService;

@Controller
public class TaxController {
	@Autowired
	ITaxService taxService;
	
	@Autowired
	ITaxPriceService taxpriceService;
	
	@GetMapping("/tax")
	public String home(HttpServletRequest request) {
		request.setAttribute("listTax", taxService.getList());
		return "ListTax";
	}
	
	
	@GetMapping("/taxprice")
	public String home1(HttpServletRequest request  ) {
		request.setAttribute("listTaxPrice", taxpriceService.getList());
		return "ListTaxPrice";
	}
	@GetMapping("/createtaxprice")
	public String create(@RequestParam(value = "id",required = false) int id  ,HttpServletRequest request) {
		/* request.setAttribute("listTaxPrice", taxpriceService.getList()); */
		TaxPriceDTO model = new TaxPriceDTO();
		if(id!=0) {
			model = taxpriceService.findbyid(id);
		}
		request.setAttribute("listTaxPrice", model);
		request.setAttribute("listTax", taxService.findAll());
		return "CreateTaxPrice";
	}

}
