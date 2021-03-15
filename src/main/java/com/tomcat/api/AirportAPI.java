package com.tomcat.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.dto.AirportDTO;
import com.tomcat.service.IAirportService;

@RestController
@RequestMapping("/api")
public class AirportAPI {

	@Autowired
	private IAirportService airportService;
	
	@GetMapping(value = "/airports")
	public ResponseEntity<List<AirportDTO>> getAirports() {
		List<AirportDTO> airportDTOs = airportService.getList();
		if(airportDTOs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(airportDTOs, HttpStatus.OK) ;
	}
	
	@GetMapping(value = "/airports/{id}")
	public ResponseEntity<AirportDTO> getAirport(@PathVariable("id") String id) {
		AirportDTO airportDTO = airportService.get(id);
		if(airportDTO.getAirport_Id() == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(airportDTO, HttpStatus.OK) ;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/airports", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AirportDTO> addAirport(@Valid @RequestBody AirportDTO airportDTO) {
		AirportDTO _airportDTO = airportService.get(airportDTO.getAirport_Id());
		if(_airportDTO.getAirport_Id() == null) {
			airportService.save(airportDTO);
			return new ResponseEntity<>(airportDTO, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/airports/{id}")
	public ResponseEntity<HttpStatus> deleteAirport(@PathVariable String id) {
		try{
			airportService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return  new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/airports/{id}")
	public ResponseEntity<AirportDTO> updateAirport(@Valid @RequestBody AirportDTO airportDTO,
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
