package com.tomcat.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.dto.TaxDTO;
import com.tomcat.dto.TaxPriceDTO;
import com.tomcat.service.ITaxService;

@RestController
public class TaxAPI {
	
	@Autowired
	ITaxService taxService;
	
	@PostMapping("api/tax")
	public TaxDTO createTaxPrice  (@RequestBody TaxDTO taxpriceDTO) {
		return taxService.save(taxpriceDTO);
		
	}
	@PutMapping("api/tax")
	public TaxDTO updateTaxPrice  (@RequestBody TaxDTO taxDTO) {
		return taxService.save(taxDTO);
		/* taxpriceService.save(taxpriceDTO); */
		
	}
	@DeleteMapping("api/tax")
	public void deleteTaxPrice  (@RequestBody int[] ids) {
		taxService.delete(ids);
		
	}
	
}
