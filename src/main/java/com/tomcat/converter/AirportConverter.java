package com.tomcat.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomcat.dto.AirportDTO;
import com.tomcat.dto.CityDTO;
import com.tomcat.entity.Airport;
import com.tomcat.entity.City;

@Component
public class AirportConverter { 

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CityConverter cityConverter;
	
	public Airport toAirport(AirportDTO airportDTO) {
		Airport airport = modelMapper.map(airportDTO, Airport.class);
//		if(airportDTO.getCity() != null) {
//			City city = cityConverter.toCity(airportDTO.getCity());
//			airport.setCity(city);
//		}
		return airport;
	}
	
	public AirportDTO toAirportDTO(Airport airport) {
		AirportDTO airportDTO = new AirportDTO();
		airportDTO.setAirport_Id(airport.getAirport_Id());
		airportDTO.setName(airport.getName());
		CityDTO city = cityConverter.toCityDTO(airport.getCity());
//		city.setAirports(null); 
		airportDTO.setCity(city);
		return airportDTO;
	}
}
