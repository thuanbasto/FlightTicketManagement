package com.tomcat.converter;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomcat.dto.AirplaneDTO;
import com.tomcat.dto.AirportDTO;
import com.tomcat.dto.SearchDTO;
import com.tomcat.dto.TaxDTO;
import com.tomcat.dto.TravelClassDTO;
import com.tomcat.service.IAirportService;
import com.tomcat.service.ITaxService;
import com.tomcat.service.ITravelClassService;

@Component
public class SearchConverter {
	
	@Autowired
	private IAirportService airportService;
	
	@Autowired
	private ITaxService taxService;
	
	@Autowired
	private ITravelClassService travelClassService;

	public SearchDTO toSearchDTO(Object[] obj) {
		SearchDTO searchDTO = new SearchDTO();
		searchDTO.setFlight_Id(Integer.valueOf(String.valueOf(obj[0])));
		
		AirportDTO fromAirport = airportService.get(String.valueOf(obj[1]));
		searchDTO.setFromAirport(fromAirport);
		AirportDTO toAirport = airportService.get(String.valueOf(obj[2]));
		searchDTO.setToAirport(toAirport);
		AirplaneDTO airplaneDTO = new AirplaneDTO();
		airplaneDTO.setAirplane_Id(String.valueOf(obj[6]));
		searchDTO.setAirplane(airplaneDTO);
		searchDTO.setArrivalDate((Date) obj[4]);
		searchDTO.setDepartureDate((Date) obj[3]);
		searchDTO.setFlight_Price(Double.valueOf(String.valueOf(obj[5])));
		searchDTO.setSeat_Id(String.valueOf(obj[7]));
		
		TravelClassDTO travelClassDTO = travelClassService.getTravelClass(String.valueOf(obj[8]));
		searchDTO.setTravelClass(travelClassDTO);
		List<TaxDTO> taxDTOs = taxService.getList();
		searchDTO.setTax(taxDTOs);
		return searchDTO;
	}
}
