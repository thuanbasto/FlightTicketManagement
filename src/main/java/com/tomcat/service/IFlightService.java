package com.tomcat.service;

import java.util.Date;
import java.util.List;

import com.tomcat.dto.FlightDTO;

public interface IFlightService {

	public List<FlightDTO> getFlights();
	
	public FlightDTO getFlight(Integer id);
	
	public void save(FlightDTO flightDTO);
	
	public void delete(Integer id);
	
	public List<FlightDTO> getFlights(String from,String to,Date departureDate,String number);
	
	public FlightDTO getEmptySeatOfFlight(String id);
}
