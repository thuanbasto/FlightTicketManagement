package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomcat.converter.TaxConverter;
import com.tomcat.dto.TaxDTO;
import com.tomcat.entity.Tax;
import com.tomcat.repository.TaxRepository;
import com.tomcat.service.ITaxService;

@Service
public class TaxService implements ITaxService{

	@Autowired
	TaxRepository taxRepository;
	
	@Autowired
	TaxConverter taxConverter;

	@Override
	public List<TaxDTO> getTaxes() {
		List<Object[]> objs = taxRepository.getTaxes();
		List<TaxDTO> taxDTOs = new ArrayList<>();
		objs.forEach(tax -> taxDTOs.add(taxConverter.toDTO(tax)));
		return taxDTOs;
	}

	@Override
	public TaxDTO save(TaxDTO taxDTO) {
		Tax tax = taxConverter.toEntity(taxDTO);
		tax = taxRepository.save(tax);
		taxDTO.setTax_Id(tax.getTax_Id());
		return taxDTO;
	}

	@Override
	public void delete(String id) {
		taxRepository.delete(Integer.valueOf(id));
	}

	@Override
	public TaxDTO getTax(String id) {
		Object[] obj = taxRepository.getTax(Integer.valueOf(id));
		if (obj.length == 0) return null;
		TaxDTO taxDTO = taxConverter.toDTO((Object[]) obj[0]);
		return taxDTO;
	}
	
}
