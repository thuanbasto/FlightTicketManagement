package com.tomcat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tomcat.service.ITaxPriceService;
import com.tomcat.service.ITaxService;

@Controller
@RequestMapping("/admin")
public class TaxController {
	@Autowired
	ITaxService taxService;
	
	@Autowired
	ITaxPriceService taxpriceService;
	
//	@GetMapping("/createtaxprice")
//	public String create(@RequestParam(value = "id",required = false) Integer id  ,HttpServletRequest request) {
//		/* request.setAttribute("listTaxPrice", taxpriceService.getList()); */
//		TaxPriceDTO model = new TaxPriceDTO();
//		if(id!=null) {
//			model = taxpriceService.findbyid(id);
//		}
//		request.setAttribute("listTaxPrice", model);
//		request.setAttribute("listTax", taxService.findAll());
//		return "CreateTaxPrice";
//	}

}
