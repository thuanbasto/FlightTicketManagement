package com.tomcat.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomcat.dto.AirplaneDTO;
import com.tomcat.dto.AirportDTO;
import com.tomcat.dto.FlightDTO;
import com.tomcat.entity.Flight;
import com.tomcat.service.IAirplaneService;
import com.tomcat.service.IAirportService;

@Component
public class FlightConverter {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AirplaneConverter airplaneConverter;

	@Autowired
	private AirportConverter airportConverter;

	@Autowired
	private IAirportService airportService;
	
	@Autowired
	private IAirplaneService airplaneService;

	public Flight toFlight(FlightDTO flightDTO) {
		return modelMapper.map(flightDTO, Flight.class);
	}

	public FlightDTO toFlightDTO(Flight flight) {
		FlightDTO flightDTO = new FlightDTO();
		flightDTO.setFlight_Id(flight.getFlight_Id());
		flightDTO.setAirplane(airplaneConverter.toAirplaneDTO(flight.getAirplane()));
		flightDTO.setArrivalDate(flight.getArrivalDate());
		flightDTO.setDepartureDate(flight.getDepartureDate());
		flightDTO.setFlight_Price(flight.getFlight_Price());
		flightDTO.setFromAirport(airportConverter.toAirportDTO(flight.getFromAirport()));
		flightDTO.setToAirport(airportConverter.toAirportDTO(flight.getToAirport()));
		return flightDTO;
//		return modelMapper.map(flight, FlightDTO.class);
	}

	/*
	 * public FlightDTO toFlightDTO(Object[] obj) { FlightDTO flightDTO = new
	 * FlightDTO(); flightDTO.setFlight_Id(Integer.valueOf(String.valueOf(obj[0])));
	 * 
	 * AirportDTO fromAirport = airportService.get(String.valueOf(obj[1]));
	 * flightDTO.setFromAirport(fromAirport);
	 * 
	 * 
	 * AirportDTO toAirport = airportService.get(String.valueOf(obj[2]));
	 * flightDTO.setToAirport(toAirport); AirplaneDTO airplaneDTO = new
	 * AirplaneDTO(); airplaneDTO.setAirplane_Id(String.valueOf(obj[6]));
	 * flightDTO.setAirplane(airplaneDTO); flightDTO.setArrivalDate((Date) obj[4]);
	 * flightDTO.setDepartureDate((Date) obj[3]);
	 * flightDTO.setFlight_Price(Double.valueOf(String.valueOf(obj[5])));
	 * flightDTO.setTickets(null); return flightDTO; }
	 */

	public FlightDTO toDTO(Object[] obj) {
		FlightDTO flightDTO = new FlightDTO();
		flightDTO.setFlight_Id(Integer.valueOf(String.valueOf(obj[0])));

		AirportDTO fromAirport = airportService.get(String.valueOf(obj[1]));
		flightDTO.setFromAirport(fromAirport);
		AirportDTO toAirport = airportService.get(String.valueOf(obj[2]));
		flightDTO.setToAirport(toAirport);
		
		flightDTO.setDepartureDate((Date) obj[3]);
		flightDTO.setArrivalDate((Date) obj[4]);
		flightDTO.setFlight_Price(Double.valueOf(String.valueOf(obj[5])));

		AirplaneDTO airplaneDTO = airplaneService.getAirplane(String.valueOf(obj[6]));
		flightDTO.setAirplane(airplaneDTO);
	
		/* flightDTO.setSeat_Id(String.valueOf(obj[7])); cos the lay */
//		flightDTO.setTravelClass_Id(Integer.valueOf(String.valueOf(obj[8])));
//
//		List<Integer> listOfTravelClass_Id = new ArrayList<Integer>();
//		listOfTravelClass_Id.add(flightDTO.getTravelClass_Id());
//		flightDTO.setListOfTravelClass_Id(listOfTravelClass_Id);
		

		return flightDTO;
	}
	
	public FlightDTO toDTOfindSeat(Object[] obj) {
		FlightDTO flightDTO = new FlightDTO();
		flightDTO.setFlight_Id(Integer.valueOf(String.valueOf(obj[0])));
		
		AirportDTO fromAirport = airportService.get(String.valueOf(obj[1]));
		flightDTO.setFromAirport(fromAirport);
		AirportDTO toAirport = airportService.get(String.valueOf(obj[2]));
		flightDTO.setToAirport(toAirport);
		AirplaneDTO airplaneDTO = new AirplaneDTO();
		airplaneDTO.setAirplane_Id(String.valueOf(obj[6]));
		flightDTO.setAirplane(airplaneDTO);
		flightDTO.setArrivalDate((Date) obj[4]);
		flightDTO.setDepartureDate((Date) obj[3]);
		flightDTO.setFlight_Price(Double.valueOf(String.valueOf(obj[5])));
		flightDTO.setSeat_Id(String.valueOf(obj[7]));
		flightDTO.setTravelClass_Id(Integer.valueOf(String.valueOf(obj[8])));
		
		
		 List<String> listOfSeat_Id = new ArrayList<String>();
		 listOfSeat_Id.add(flightDTO.getSeat_Id());
		  
		 flightDTO.setListOfSeat_Id(listOfSeat_Id);
		 
		
		return flightDTO;
	}


}
