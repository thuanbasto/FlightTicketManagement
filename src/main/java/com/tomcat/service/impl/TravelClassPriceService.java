package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.converter.TravelClassPriceConverter;
import com.tomcat.dto.TravelClassPriceDTO;
import com.tomcat.entity.TravelclassPrice;
import com.tomcat.repository.TravelClassPriceRepository;
import com.tomcat.service.ITravelClassPriceService;

@RestController
public class TravelClassPriceService implements ITravelClassPriceService{

	@Autowired
	TravelClassPriceConverter travelClassPriceConverter;
	
	@Autowired
	TravelClassPriceRepository travelClassPriceRepository;
	
	@Override
	@Transactional
	public List<TravelClassPriceDTO> getTravelClassPrices() {
		List<TravelclassPrice> travelClassPrices = travelClassPriceRepository.findAll();
		List<TravelClassPriceDTO> travelClassPriceDTOs = new ArrayList<TravelClassPriceDTO>(); 
		travelClassPrices.forEach(travelClassPrice -> travelClassPriceDTOs.add(travelClassPriceConverter.toDTO(travelClassPrice)));
		return travelClassPriceDTOs;
		
	}

	@Override
	@Transactional
	public TravelClassPriceDTO getTravelClassPrice(String id) {
		TravelclassPrice travelClassPrice = travelClassPriceRepository.findOne(Integer.valueOf(id));
		if(travelClassPrice != null) {
			TravelClassPriceDTO travelClassPriceDTO = travelClassPriceConverter.toDTO(travelClassPrice);
			return travelClassPriceDTO;
		}
		return new TravelClassPriceDTO();
	}

	@Override
	public void save(TravelClassPriceDTO travelClassPriceDTO) {
		TravelclassPrice travelClassPrice = travelClassPriceConverter.toEntity(travelClassPriceDTO);
		travelClassPriceRepository.save(travelClassPrice);
	}

	@Override
	public void delete(String id) {
		travelClassPriceRepository.delete(Integer.valueOf(id));
	}

}
