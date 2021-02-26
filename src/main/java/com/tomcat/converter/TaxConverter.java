package com.tomcat.converter;

import org.springframework.stereotype.Component;

import com.tomcat.dto.TaxDTO;
import com.tomcat.entity.Tax;

@Component
public class TaxConverter {
	public TaxDTO toDTO (Tax entity){
		TaxDTO taxDTO = new TaxDTO();
		taxDTO.setTax_Id(entity.getTax_Id());
		taxDTO.setTaxName(entity.getTaxName());
		return taxDTO;
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
