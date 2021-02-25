package com.tomcat.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.dto.FlightDTO;
import com.tomcat.service.IFlightService;

@RestController
@RequestMapping("/api")
public class FlightAPI {

	@Autowired
	private IFlightService flightService;

	@GetMapping("/flights")
	public ResponseEntity<List<FlightDTO>> getFlights() {
		List<FlightDTO> flightDTOs = flightService.getFlights();
		if (flightDTOs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<FlightDTO>>(flightDTOs, HttpStatus.OK);
		}
	}

	@GetMapping("/flights/{id}")
	public ResponseEntity<FlightDTO> getFlight(@PathVariable("id") String id) {
		FlightDTO flightDTO = flightService.getFlight(Integer.valueOf(id));
		if (flightDTO.getFlight_Id() > 0) {
			return new ResponseEntity<FlightDTO>(flightDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/flights")
	public ResponseEntity<FlightDTO> updateFlight(@RequestBody FlightDTO flightDTO) {
		flightService.save(flightDTO);
		return new ResponseEntity<FlightDTO>(flightDTO, HttpStatus.OK);
	}

	@PutMapping("/flights/{id}")
	public ResponseEntity<FlightDTO> updateFlight(@RequestBody FlightDTO flightDTO, @PathVariable("id") String id) {
		FlightDTO _flightDTO = flightService.getFlight(Integer.valueOf(id));
		if (_flightDTO.getFlight_Id() != null) {
			flightService.save(flightDTO);
			return new ResponseEntity<FlightDTO>(flightDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/flights/{id}")
	public ResponseEntity<HttpStatus> deleteFlight(@PathVariable("id") String id) {
		FlightDTO _flightDTO = flightService.getFlight(Integer.valueOf(id));
		if (_flightDTO.getFlight_Id() != null) {
			flightService.delete(Integer.valueOf(id));
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
