package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<FlightDTO> getFlights(String from, String to, Date departureDate, String number) {
		List<Object[]> objs = flightRepository.searchFlight(from, to, departureDate, number);
		Map<Integer, FlightDTO> flightDTOs = new HashMap<Integer, FlightDTO>();
		objs.forEach(flight -> {
			FlightDTO flightDTO = flightConverter.toDTO(flight);
			
			if (flightDTOs.containsKey((flightDTO).getFlight_Id())) {
				FlightDTO flightInMap = flightDTOs.get(flightDTO.getFlight_Id());
				if (flightInMap.getTravelClass_Id() != flightDTO.getTravelClass_Id()) {
					List<Integer> listOfTravelClass_Id = flightInMap.getListOfTravelClass_Id();
					listOfTravelClass_Id.add(flightDTO.getTravelClass_Id());
					
					flightInMap.setListOfTravelClass_Id(listOfTravelClass_Id);
					flightDTOs.put(flightDTO.getFlight_Id(),flightInMap);
				}
			} else {
				flightDTOs.put(flightDTO.getFlight_Id(),flightDTO);
			}
		});
		
		return new ArrayList<FlightDTO>(flightDTOs.values());
	}

	
}
