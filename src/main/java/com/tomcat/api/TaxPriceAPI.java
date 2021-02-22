package com.tomcat.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.dto.TaxPriceDTO;
import com.tomcat.service.ITaxPriceService;

@RestController
@RequestMapping("/api")
public class TaxPriceAPI {
	
	@Autowired
	ITaxPriceService taxpriceService;
	
	@PostMapping("/taxprice")
	public TaxPriceDTO createTaxPrice  (@RequestBody TaxPriceDTO taxpriceDTO) {
		return taxpriceService.save(taxpriceDTO);
	}
	
	@PutMapping("/taxprice")
	public TaxPriceDTO updateTaxPrice  (@RequestBody TaxPriceDTO taxpriceDTO) {
		return taxpriceService.save(taxpriceDTO);
	}
	
	@DeleteMapping("/taxprice")
	public void deleteTaxPrice  (@RequestBody int[] ids) {
		taxpriceService.delete(ids);
		
	}

}
