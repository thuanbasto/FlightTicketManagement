package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomcat.converter.TravelclassConverter;
import com.tomcat.dto.TravelclassDTO;
import com.tomcat.entity.Travelclass;
import com.tomcat.repository.TravelclassRepository;
import com.tomcat.service.ITravelclassService;

@Service
public class TravelclassService implements ITravelclassService{
	@Autowired
	TravelclassRepository travelclassRepository;
	
	@Autowired
	TravelclassConverter travelclassConverter;

	@Override
	public List<TravelclassDTO> getList() {
		List<TravelclassDTO> model =new  ArrayList<>();
		List<Travelclass> entitis = travelclassRepository.findAll();
		for(Travelclass item : entitis) {
			model.add(travelclassConverter.toDTO(item));
		}
		return model;
	}

	@Override
	public TravelclassDTO findbyid(int id) {
		Travelclass entity = travelclassRepository.findOne(id);
		return travelclassConverter.toDTO(entity);
	}

	@Override
	public void save(TravelclassDTO travelclassDTO) {
		Travelclass entity = travelclassConverter.toEntity(travelclassDTO);
		travelclassRepository.save(entity);
		
	}

	@Override
	public void delete(int id) {
		travelclassRepository.delete(id);
		
	}

}
