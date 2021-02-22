package com.tomcat.converter;

import org.springframework.stereotype.Component;

import com.tomcat.dto.TaxDTO;
import com.tomcat.entity.Tax;

@Component
public class TaxConverter {
	public TaxDTO todto (Tax entity)
	{
		TaxDTO result =new  TaxDTO();
		result.setTax_Id(entity.getTax_Id());
		result.setTaxName(entity.getTaxName());
		result.setTaxPrices(null);
		result.setTickets(null);
		return result;
	}
	
	public Tax toentyti (TaxDTO taxdto)
	{
		Tax result = new Tax();
		result.setTax_Id(taxdto.getTax_Id());
		result.setTaxName(taxdto.getTaxName());
		return result;
	}
	public Tax toentyti ( Tax result,TaxDTO taxdto)
	{
		
		result.setTax_Id(taxdto.getTax_Id());
		result.setTaxName(taxdto.getTaxName());
		return result;
	}


}
