package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tomcat.converter.CityConverter;
import com.tomcat.dto.CityDTO;
import com.tomcat.entity.Airport;
import com.tomcat.entity.City;
import com.tomcat.repository.CityRepository;
import com.tomcat.service.IAirportService;
import com.tomcat.service.ICityService;

@Service
@Transactional
public class CityService implements ICityService{

	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	private CityConverter cityConverter;
	
	@Autowired
	private IAirportService airportService;
	
	@Override
	@Transactional
	public List<CityDTO> getList() {
		List<City> cities =  cityRepository.findAll();
		cities.forEach(city -> Hibernate.initialize(city.getAirports()));
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
	@Transactional
	public CityDTO get(String id) {
		City city = cityRepository.findOne(id);
		Hibernate.initialize(city.getAirports());
		if(city.getCity_Id() != null) {
			return cityConverter.toCityDTO(city);
		}
		return new CityDTO();
	}

	@Override
	@Transactional
	public List<City> getList1() {
		List<City> cities = cityRepository.findAll();
		cities.forEach(city -> Hibernate.initialize(city.getAirports()));
		return cities;
	}
	
}
