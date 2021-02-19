package com.tomcat.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.dto.TaxDTO;
import com.tomcat.dto.TaxPriceDTO;
import com.tomcat.service.ITaxService;

@RestController
@RequestMapping("/api")
public class TaxAPI {
	
	@Autowired
	ITaxService taxService;
	
	@PostMapping("/tax")
	public TaxDTO createTaxPrice  (@RequestBody TaxDTO taxpriceDTO) {
		return taxService.save(taxpriceDTO);
		
	}
	@PutMapping("/tax")
	public TaxPriceDTO updateTaxPrice  (@RequestBody TaxPriceDTO taxpriceDTO) {
		return taxpriceDTO;
		/* taxpriceService.save(taxpriceDTO); */
		
	}
	@DeleteMapping("/tax")
	public void deleteTaxPrice  (@RequestBody int[] ids) {
		taxService.delete(ids);
		
	}
	
}
