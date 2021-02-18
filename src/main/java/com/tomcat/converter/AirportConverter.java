package com.tomcat.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomcat.dto.AirportDTO;
import com.tomcat.entity.Airport;

@Component
public class AirportConverter { 

	@Autowired
	private ModelMapper modelMapper;
	
	public Airport toAirport(AirportDTO airportDTO) {
		Airport airport = modelMapper.map(airportDTO, Airport.class);
		return airport;
	}
	
	public AirportDTO toAirportDTO(Airport airport) {
		AirportDTO airportDTO = modelMapper.map(airport, AirportDTO.class);
		return airportDTO;
	}
	
	
}
