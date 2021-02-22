package com.tomcat.converter;

import org.springframework.stereotype.Component;

import com.tomcat.dto.TaxDTO;
import com.tomcat.entity.Tax;

@Component
public class TaxConverter {
	public TaxDTO toDTO(Tax entity)
	{
		TaxDTO result =new  TaxDTO();
		result.setTax_Id(entity.getTax_Id());
		result.setTaxName(entity.getTaxName());
		result.setTaxPrices(null);
		result.setTickets(null);
		return result;
	}
	
	public Tax toEntity (TaxDTO taxDTO){
		Tax tax = new Tax();
		tax.setTax_Id(taxDTO.getTax_Id());
		tax.setTaxName(taxDTO.getTaxName());
		return tax;
	}
	public Tax toEntity (Tax tax,TaxDTO taxDTO){
		tax.setTax_Id(taxDTO.getTax_Id());
		tax.setTaxName(taxDTO.getTaxName());
		return tax;
	}
}
