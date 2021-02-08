package com.tomcat.converter;

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
		List<AirportDTO> airportDTOs = cityDTO.getAirports();
		if(airportDTOs != null) {
			airportDTOs.forEach(airportDTO -> city.addAirport(modelMapper.map(airportDTO, Airport.class)));
		}
		return city;
	}
	
	public CityDTO toCityDTO(City city) {
		CityDTO cityDTO = modelMapper.map(city, CityDTO.class);
		List<Airport> airports = city.getAirports();
		if(airports != null)
		{
			airports.forEach(airport -> cityDTO.addAirport(modelMapper.map(airport, AirportDTO.class)));
		}
		return cityDTO;
	}
	
}
