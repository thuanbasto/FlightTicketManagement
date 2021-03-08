package com.tomcat.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tomcat.dto.TaxDTO;
import com.tomcat.dto.TaxPriceDTO;
import com.tomcat.entity.Tax;

@Component
public class TaxConverter {
	public TaxDTO toDTO (Object[] obj){
		TaxDTO taxDTO = new TaxDTO();
		taxDTO.setTax_Id(Integer.valueOf(String.valueOf(obj[0])));
		taxDTO.setTaxName(String.valueOf(obj[1]));
		
		TaxPriceDTO taxPriceDTO = new TaxPriceDTO();
		taxPriceDTO.setTax_Price_Id(Integer.valueOf(String.valueOf(obj[2])));
		taxPriceDTO.setPrice(Double.valueOf(String.valueOf(obj[3])));
		taxPriceDTO.setModifiedDate((Date) obj[4]);
		
		List<TaxPriceDTO> taxPriceDTOs = new ArrayList<TaxPriceDTO>();
		taxPriceDTOs.add(taxPriceDTO);
		
		taxDTO.setTaxPrices(taxPriceDTOs);
		
		return taxDTO;
	}
	
	public Tax toEntity (TaxDTO taxDTO){
		Tax tax = new Tax();
		tax.setTax_Id(taxDTO.getTax_Id());
		tax.setTaxName(taxDTO.getTaxName());
		return tax;
	}
}
