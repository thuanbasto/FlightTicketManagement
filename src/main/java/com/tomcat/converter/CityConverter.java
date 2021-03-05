package com.tomcat.converter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomcat.dto.CityDTO;
import com.tomcat.dto.RoleDTO;
import com.tomcat.entity.City;
import com.tomcat.entity.Role;

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
	public Set<CityDTO> toCityDTO(List<City> listCity) {
		Set<CityDTO> listCityDTO = new HashSet<CityDTO>();
		listCity.forEach(city -> {
			CityDTO cityDTO = new CityDTO();
			cityDTO.setCity_Id(city.getCity_Id());
			cityDTO.setName(city.getName());
			listCityDTO.add(cityDTO);
		});
		return listCityDTO;
	}
	
}
