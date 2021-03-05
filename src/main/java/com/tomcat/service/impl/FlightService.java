package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tomcat.converter.FlightConverter;
import com.tomcat.dto.FlightDTO;
import com.tomcat.entity.Flight;
import com.tomcat.repository.FlightRepository;
import com.tomcat.service.IFlightService;

@Service
public class FlightService implements IFlightService{

	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private FlightConverter flightConverter;
	
	@Transactional
	public List<FlightDTO> getFlights(){
		List<Flight> flights = flightRepository.findAll();
		List<FlightDTO> flightDTOs = new ArrayList<>();
		flights.forEach(flight->flightDTOs.add(flightConverter.toFlightDTO(flight)));
		return flightDTOs;
	}

	@Override
	@Transactional
	public FlightDTO getFlight(Integer id) {
		Flight flight = flightRepository.findOne(id);
		if(flight != null) {
			return flightConverter.toFlightDTO(flight);
		}else {
			return new FlightDTO();
		}
	}

	@Override
	public void save(FlightDTO flightDTO) {
		Flight flight = flightConverter.toFlight(flightDTO);
		flightRepository.save(flight);
	}

	@Override
	public void delete(Integer id) {
		flightRepository.delete(id);
	}

	@Override
	public List<FlightDTO> getseachsFlights(String id) {
		List<Object[]> flights = flightRepository.findSeach(id);
		List<FlightDTO> flightDTOs = new ArrayList<>();
		flights.forEach(flight->flightDTOs.add(flightConverter.toFlightDTO(flight)));
		return flightDTOs;
	}
}
