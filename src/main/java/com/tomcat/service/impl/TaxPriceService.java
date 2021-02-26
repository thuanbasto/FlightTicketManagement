package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tomcat.converter.TaxPriceConverter;
import com.tomcat.dto.TaxPriceDTO;
import com.tomcat.entity.Tax;
import com.tomcat.entity.TaxPrice;
import com.tomcat.repository.TaxPriceRepository;
import com.tomcat.repository.TaxRepository;
import com.tomcat.service.ITaxPriceService;

@Service
public class TaxPriceService implements ITaxPriceService{
	
	@Autowired
	TaxPriceRepository taxpriceRepositpory;
	
	@Autowired
	TaxPriceConverter taxpriceConverter;
	
	@Autowired
	TaxRepository taxRepository;

	@Override
	@Transactional
	public List<TaxPriceDTO> getList() {
		  List<TaxPriceDTO> models = new ArrayList<>(); 
		  List<TaxPrice> entytis= taxpriceRepositpory.findAll(); 
		  for (TaxPrice item : entytis) { 
			  TaxPriceDTO taxpriceDTO = taxpriceConverter.toDto(item);
			  models.add(taxpriceDTO);   
		  }
		return models;
	}

	@Override
	@Transactional
	public TaxPriceDTO findbyid(Integer id) {
		TaxPrice entyti = taxpriceRepositpory.findOne(id);
		if(entyti != null) {
			TaxPriceDTO taxpriceDTO = taxpriceConverter.toDto(entyti);
			return taxpriceDTO;
		}
		return new TaxPriceDTO();
	}


	@Override
	@Transactional
	public void save(TaxPriceDTO dto) {
		Tax tax = taxRepository.findOne(dto.getTax_Id());
		TaxPrice taxEntity = taxpriceConverter.toEntity(dto);
		taxEntity.setTax(tax);
		taxpriceRepositpory.save(taxEntity);
		
		
	}
	@Override
	public void delete(int id) {
		taxpriceRepositpory.delete(id);
	}

}
