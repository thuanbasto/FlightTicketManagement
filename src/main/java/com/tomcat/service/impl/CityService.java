package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tomcat.converter.CityConverter;
import com.tomcat.dto.CityDTO;
import com.tomcat.entity.City;
import com.tomcat.repository.CityRepository;
import com.tomcat.service.ICityService;

@Service
@Transactional
public class CityService implements ICityService{

	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	private CityConverter cityConverter;
	
	
	@Override
	public List<CityDTO> getList() {
		List<City> cities =  cityRepository.findAll();
		List<CityDTO> cityDTOs = new ArrayList<>();
		cities.forEach(city -> cityDTOs.add(cityConverter.toCityDTO(city)));
		return cityDTOs;
	}

	@Override
	public void save(CityDTO cityDTO) {
		City city = cityConverter.toCity(cityDTO);
		cityRepository.save(city);
	}


	@Override
	public void delete(String id) {
		cityRepository.delete(id);
	}

	@Override
	public CityDTO get(String id) {
		City city = cityRepository.findOne(id);
		if(city != null) {
			return cityConverter.toCityDTO(city);
		}
		return new CityDTO();
	}

}
