package com.tomcat.converter;

import org.springframework.stereotype.Component;
import com.tomcat.dto.TaxPriceDTO;
import com.tomcat.entity.TaxPrice;

@Component
public class TaxPriceConverter {
	public TaxPriceDTO toDto (TaxPrice entity)
	{
		TaxPriceDTO result =new  TaxPriceDTO();
		result.setModifiedDate(entity.getModifiedDate());
		result.setPrice(entity.getPrice());
		result.setTax_Price_Id(entity.getTax_Price_Id());
		result.setTax_Id(entity.getTax().getTax_Id());
		return result;
	}
	
	public TaxPrice toEntity (TaxPriceDTO dto) {
		TaxPrice result = new TaxPrice();
		result.setModifiedDate(dto.getModifiedDate());
		result.setPrice(dto.getPrice());
		result.setTax_Price_Id(dto.getTax_Price_Id());
		return result;
	}
	public TaxPrice toEntity (TaxPrice result,TaxPriceDTO dto) {
		result.setModifiedDate(dto.getModifiedDate());
		result.setPrice(dto.getPrice());
		result.setTax_Price_Id(dto.getTax_Price_Id());
		return result;
	}
	

}
