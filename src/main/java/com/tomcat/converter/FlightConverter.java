package com.tomcat.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomcat.dto.FlightDTO;
import com.tomcat.entity.Flight;

@Component
public class FlightConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AirplaneConverter airplaneConverter;
	
	@Autowired 
	private AirportConverter airportConverter;
	
	public Flight toFlight(FlightDTO flightDTO) {
		return modelMapper.map(flightDTO, Flight.class);
	}
	
	public FlightDTO toFlightDTO(Flight flight) {
		FlightDTO flightDTO = new FlightDTO();
		flightDTO.setFlight_Id(flight.getFlight_Id());
		flightDTO.setAirplane(airplaneConverter.toAirplaneDTO(flight.getAirplane()));
		flightDTO.setArrivalDate(flight.getArrivalDate());
		flightDTO.setDeparutreDate(flight.getDeparutreDate());
		flightDTO.setFlight_Price(flight.getFlight_Price());
		flightDTO.setFromAirport(airportConverter.toAirportDTO(flight.getFromAirport()));
		flightDTO.setToAriport(airportConverter.toAirportDTO(flight.getToAirport()));
		return flightDTO;
//		return modelMapper.map(flight, FlightDTO.class);
	}

}
