package com.tomcat.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.dto.AirportDTO;
import com.tomcat.service.IAirportService;

@RestController
@RequestMapping("/api")
public class AirportAPI {

	@Autowired
	private IAirportService airportService;
	
	@RequestMapping(value = "/airports", method = RequestMethod.GET)
	public ResponseEntity<List<AirportDTO>> getAirports() {
		List<AirportDTO> airportDTOs = airportService.getList();
		if(airportDTOs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(airportDTOs, HttpStatus.OK) ;
	}
	
	@RequestMapping(value = "/airports/{id}", method = RequestMethod.GET)
	public ResponseEntity<AirportDTO> getAirport(@PathVariable("id") String id) {
		AirportDTO airportDTO = airportService.get(id);
		if(airportDTO.getAirport_Id() == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(airportDTO, HttpStatus.OK) ;
	}
	
	@RequestMapping(value = "/airports", method = RequestMethod.POST)
	public ResponseEntity<AirportDTO> addAirport(@RequestBody AirportDTO airportDTO) {
		AirportDTO _airportDTO = airportService.get(airportDTO.getAirport_Id());
		if(_airportDTO.getAirport_Id() == null) {
			airportService.save(airportDTO);
			return new ResponseEntity<>(airportDTO, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/airports/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteAirport(@PathVariable String id) {
		try{
			airportService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return  new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@RequestMapping(value = "/airports/{id}", method = RequestMethod.PUT)
	public ResponseEntity<AirportDTO> updateAirport(@RequestBody AirportDTO airportDTO,
			@PathVariable String id) {
		AirportDTO _airportDTO = airportService.get(id);
		if(_airportDTO.getAirport_Id() != null) {
			airportService.save(airportDTO);
			return new ResponseEntity<>(airportDTO, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
