package com.tomcat.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomcat.dto.AirportDTO;
import com.tomcat.dto.CityDTO;
import com.tomcat.entity.Airport;
import com.tomcat.entity.City;

@Component
public class CityConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public City toCity(CityDTO cityDTO) {
		City city = modelMapper.map(cityDTO, City.class);
		return city;
		
//		City city = new City();
//		city.setCity_Id(cityDTO.getCity_Id());
//		city.setName(cityDTO.getName());
//		List<Airport> airports = new ArrayList<>();
//		
//		for(AirportDTO airportDTO : cityDTO.getAirports()) {
//			Airport airport = airportConverter.toAirport(airportDTO);
//			airports.add(airport);
//		}
//		city.setAirports(airports);
//		return city;
	}
	
	public CityDTO toCityDTO(City city) {
//		CityDTO cityDTO = modelMapper.map(city, CityDTO.class);
//		return cityDTO;
		
		CityDTO cityDTO = new CityDTO();
		cityDTO.setCity_Id(city.getCity_Id());
		cityDTO.setName(city.getName());
		cityDTO.setAirports(null);
		return cityDTO;
	}
	
}
