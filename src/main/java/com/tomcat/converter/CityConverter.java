package com.tomcat.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomcat.dto.CityDTO;
import com.tomcat.entity.City;

@Component
public class CityConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public City toCity(CityDTO cityDTO) {
		City city = modelMapper.map(cityDTO, City.class);
		return city;
	}
	
	public CityDTO toCityDTO(City city) {
		CityDTO cityDTO = modelMapper.map(city, CityDTO.class);
		return cityDTO;
	}
	
}