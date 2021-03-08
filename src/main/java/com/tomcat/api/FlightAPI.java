package com.tomcat.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<FlightDTO> addFlight(@RequestBody FlightDTO flightDTO) {
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

	@GetMapping("/search")
	public ResponseEntity<List<FlightDTO>> getSeachFlight(
			@RequestParam(name="from",required=false) String from,
			@RequestParam(name="to",required=false) String to,
			@RequestParam(name="departureDate",required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date departureDate,
			@RequestParam(name="number",required=false) String number) {
		// api/search?from=DAD&to=HAN&departureDate=2000-1-13 00:00:00&number=1
		if(from == null || to == null || departureDate == null || number == null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else
		{
			List<FlightDTO> flightDTOs = flightService.getFlights(from, to, departureDate, number);
			if (flightDTOs != null) {
				return new ResponseEntity<List<FlightDTO>>(flightDTOs, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}	
	}
}
