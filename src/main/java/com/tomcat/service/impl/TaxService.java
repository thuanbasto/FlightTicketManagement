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
	@Transactional
	public List<TaxDTO> getList() {
		List<TaxDTO> models = new ArrayList<>();
		List<Tax> entities = taxRepository.findAll();
		for (Tax item : entities) {
			TaxDTO taxDTO = taxConverter.toDTO(item);
			models.add(taxDTO);			
		}
		return models;
	}

	@Override
	public Map<Integer, String> findAll() {
		Map<Integer, String> model = new HashMap<>();
		List<Tax> entities = taxRepository.findAll();
		for (Tax item : entities) {
			model.put(item.getTax_Id(),item.getTaxName());			
		}
		return model;
	}

	@Override
	@Transactional
	public TaxDTO save(TaxDTO dto) {
		Tax taxEntity = new Tax();
		if(dto.getTax_Id()!=0) {
			Tax taxOne = taxRepository.findOne(dto.getTax_Id());
			taxEntity = taxConverter.toEntity(taxOne, dto);
		}
		else {
			taxEntity = taxConverter.toEntity(dto);
		}
		
		Tax a =taxRepository.save(taxEntity);
		return taxConverter.toDTO(a);
	}

	@Override
	@Transactional
	public void delete(int[] ids) {
		for(int id :ids) {
			taxRepository.delete(id);
		}
	}

	@Override
	public TaxDTO findbyid(Integer id) {
		Tax tax = taxRepository.findOne(id);
		return taxConverter.toDTO(tax);
	}
	
}
