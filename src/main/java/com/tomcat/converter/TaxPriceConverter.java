package com.tomcat.converter;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.tomcat.dto.TaxPriceDTO;
import com.tomcat.entity.Tax;
import com.tomcat.entity.TaxPrice;

@Component
public class TaxPriceConverter {
	public TaxPriceDTO toDTO (TaxPrice taxPrice) {
		TaxPriceDTO taxPriceDTO = new TaxPriceDTO();
		taxPriceDTO.setModifiedDate(taxPrice.getModifiedDate());
		taxPriceDTO.setPrice(taxPrice.getPrice());
		taxPriceDTO.setTax_Price_Id(taxPrice.getTax_Price_Id());
		taxPriceDTO.setTax_Id(taxPrice.getTax().getTax_Id());
		return taxPriceDTO;
	}
	
	public TaxPrice toEntity (TaxPriceDTO taxPriceDTO) {
		TaxPrice taxPrice = new TaxPrice();
		taxPrice.setModifiedDate(new Date());
		taxPrice.setPrice(taxPriceDTO.getPrice());
		taxPrice.setTax_Price_Id(taxPriceDTO.getTax_Price_Id());
		
		Tax tax = new Tax();
		tax.setTax_Id(taxPriceDTO.getTax_Id());
		
		taxPrice.setTax(tax);
		return taxPrice;
	}
}
