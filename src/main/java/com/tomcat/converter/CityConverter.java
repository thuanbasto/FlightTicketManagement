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
	
	@Autowired
	private AirportConverter airportConverter;
	
	public City toCity(CityDTO cityDTO) {
		City city = modelMapper.map(cityDTO, City.class);
		List<AirportDTO> airportDTOs = cityDTO.getAirports();
		if(airportDTOs != null) {
			airportDTOs.forEach(airportDTO -> city.addAirport(modelMapper.map(airportDTO, Airport.class)));
		}
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
		CityDTO cityDTO = modelMapper.map(city, CityDTO.class);
		List<Airport> airports = city.getAirports();
		if(airports != null)
		{
			airports.forEach(airport -> cityDTO.addAirport(modelMapper.map(airport, AirportDTO.class)));
		}
		return cityDTO;
		
//		CityDTO cityDTO = new CityDTO();
//		cityDTO.setCity_Id(city.getCity_Id());
//		cityDTO.setName(city.getName());
//		List<AirportDTO> airportDTOs = new ArrayList<>();
//		for(Airport airport : city.getAirports()) {
//			AirportDTO airportDTO = airportConverter.toAirportDTO(airport);
//			airportDTO.setCity(null);
//			airportDTO.setFlights1(null);
//			airportDTO.setFlights2(null);
//			airportDTOs.add(airportDTO);
//		}
//		cityDTO.setAirports(airportDTOs);
//		return cityDTO;
	}
	
}
