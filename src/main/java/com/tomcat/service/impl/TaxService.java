package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tomcat.converter.TaxConverter;
import com.tomcat.dto.TaxDTO;
import com.tomcat.dto.TaxPriceDTO;
import com.tomcat.entity.Tax;
import com.tomcat.entity.TaxPrice;
import com.tomcat.repository.TaxRepository;
import com.tomcat.service.ITaxService;

@Service
public class TaxService implements ITaxService{

	@Autowired
	TaxRepository taxRepository;
	@Autowired
	TaxConverter taxConverter;

	@Override
	@Transactional
	public List<TaxDTO> getList() {
		List<TaxDTO> models = new ArrayList<>();
		List<Tax> entytis = taxRepository.findAll();
		for (Tax item : entytis) {
			TaxDTO taxDTO = taxConverter.todto(item);
			models.add(taxDTO);			
		}
		return models;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Integer, String> findAll() {
		Map<Integer, String > model = new HashMap<>();
		List<Tax> entytis = taxRepository.findAll();
		for (Tax item : entytis) {
			
			model.put(item.getTax_Id(),item.getTaxName());			
		}
		return model;
	}

	@Override
	@Transactional
	public TaxDTO save(TaxDTO dto) {
		// TODO Auto-generated method stub
		Tax taxentity = new Tax();
		if(dto.getTax_Id()!=0) {
			Tax taxone = taxRepository.findOne(dto.getTax_Id());
			taxentity = taxConverter.toentyti(taxone, dto);
		}
		taxentity = taxConverter.toentyti(dto);
		Tax a =taxRepository.save(taxentity);
		return taxConverter.todto(a);
		
	}

	@Override
	@Transactional
	public void delete(int[] ids) {
		for(int id :ids) {
			taxRepository.delete(id);
		}
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public TaxDTO findbyid(Integer id) {
		Tax tax = taxRepository.findOne(id);
		return taxConverter.todto(tax);
	}
	
	



}
