package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tomcat.converter.AirportConverter;
import com.tomcat.dto.AirportDTO;
import com.tomcat.entity.Airport;
import com.tomcat.repository.AirportRepository;
import com.tomcat.service.IAirportService;

@Service
public class AirportService implements IAirportService{
	
	@Autowired
	AirportRepository airportRepository;
	
	@Autowired
	private AirportConverter airportConverter;
	
	@Override
	@Transactional
	public List<AirportDTO> getList() {
		List<Airport> airports =  airportRepository.findAll();
		airports.forEach(airport -> Hibernate.initialize(airport.getCity()));
		List<AirportDTO> airportDTOs = new ArrayList<>();
		airports.forEach(airport -> airportDTOs.add(airportConverter.toAirportDTO(airport)));
		return airportDTOs;
	}
	
	@Override
	public void save(AirportDTO airportDTO) {
		Airport airport = airportConverter.toAirport(airportDTO);
		airportRepository.save(airport);
	}

	@Override
	public void delete(String id) {
		airportRepository.delete(id);
	}

	@Override
	@Transactional
	public AirportDTO get(String id) {
		Airport airport = airportRepository.findOne(id);
		Hibernate.initialize(airport.getCity());
		if(airport.getAirport_Id() != null) {
			return airportConverter.toAirportDTO(airport);
		}
		return new AirportDTO();
	}
}
