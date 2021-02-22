package com.tomcat.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomcat.dto.TaxDTO;
import com.tomcat.dto.TaxPriceDTO;
import com.tomcat.entity.TaxPrice;

@Component
public class TaxPriceConverter {
	
	@Autowired
	private TaxConverter taxConverter;
	
	public TaxPriceDTO toDto (TaxPrice entity)
	{
		TaxPriceDTO taxpriceDTO =new  TaxPriceDTO();
		taxpriceDTO.setModifiedDate(entity.getModifiedDate());
		taxpriceDTO.setPrice(entity.getPrice());
		taxpriceDTO.setTax_Price_Id(entity.getTax_Price_Id());
		TaxDTO tax =taxConverter.toDTO(entity.getTax());
		taxpriceDTO.setTax(tax);
		return taxpriceDTO;
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
