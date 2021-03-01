package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tomcat.converter.TaxPriceConverter;
import com.tomcat.dto.TaxPriceDTO;
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
			  TaxPriceDTO taxpriceDTO = taxpriceConverter.toDTO(item);
			  models.add(taxpriceDTO);   
		  }
		return models;
	}

	@Override
	@Transactional
	public TaxPriceDTO findbyid(Integer id) {
		TaxPrice taxPrice = taxpriceRepositpory.findOne(id);
		if(taxPrice != null) {
			TaxPriceDTO taxpriceDTO = taxpriceConverter.toDTO(taxPrice);
			return taxpriceDTO;
		}
		return new TaxPriceDTO();
	}


	@Override
	@Transactional
	public void save(TaxPriceDTO taxPriceDTO) {
		TaxPrice taxPrice = taxpriceConverter.toEntity(taxPriceDTO);
		taxpriceRepositpory.save(taxPrice);
	}
	@Override
	public void delete(int id) {
		taxpriceRepositpory.delete(id);
	}

}