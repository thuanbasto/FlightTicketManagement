package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.converter.TravelclassPriceConverter;
import com.tomcat.dto.TravelclassDTO;
import com.tomcat.dto.TravelclassPriceDTO;
import com.tomcat.entity.Travelclass;
import com.tomcat.entity.TravelclassPrice;
import com.tomcat.repository.TravelclassPriceRepository;
import com.tomcat.repository.TravelclassRepository;
import com.tomcat.service.ITravelclassPriceService;
import com.tomcat.service.ITravelclassService;

@RestController
public class TravelclassPriceService implements ITravelclassPriceService{

	@Autowired
	TravelclassPriceConverter travelclassPriceConverter;
	
	@Autowired
	TravelclassPriceRepository travelclassPriceRepository;
	
	@Autowired
	ITravelclassService travelclassService;
	
	@Autowired
	TravelclassRepository travelclassRepository;
	
	@Override
	@Transactional
	public List<TravelclassPriceDTO> getList() {
		List<TravelclassPriceDTO> models = new ArrayList<>(); 
		  List<TravelclassPrice> entytis= travelclassPriceRepository.findAll(); 
		  for (TravelclassPrice item : entytis) { 
			  TravelclassPriceDTO travelclassPriceDTO = travelclassPriceConverter.toDTO(item);
			  models.add(travelclassPriceDTO);   
		  }
		return models;
		
	}

	@Override
	public List<TravelclassDTO> getListTravelclass() {
		List<TravelclassDTO> travelclassDTOs = travelclassService.getList();
		return travelclassDTOs;
	}

	@Override
	@Transactional
	public TravelclassPriceDTO findbyid(int id) {
		TravelclassPrice entyti = travelclassPriceRepository.findOne(id);
		if(entyti != null) {
			TravelclassPriceDTO travelclassPriceDTO = travelclassPriceConverter.toDTO(entyti);
			return travelclassPriceDTO;
		}
		return new TravelclassPriceDTO();
	}

	@Override
	public void save(TravelclassPriceDTO travelclasspriceDTO) {
		
		Travelclass travelclass = travelclassRepository.findOne(travelclasspriceDTO.getTravelClass_Id());
		TravelclassPrice travelclassPrice = travelclassPriceConverter.toEntity(travelclasspriceDTO);
		travelclassPrice.setTravelclass(travelclass);
		travelclassPriceRepository.save(travelclassPrice);
		
	}

	@Override
	public void delete(int id) {
		travelclassPriceRepository.delete(id);
		
	}

}
