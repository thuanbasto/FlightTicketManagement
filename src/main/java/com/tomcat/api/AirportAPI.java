package com.tomcat.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.dto.AirportDTO;
import com.tomcat.service.IAirportService;

@RestController
@RequestMapping("/api")
public class AirportAPI {

	@Autowired
	private IAirportService airportService;

	@RequestMapping(value = "/airports", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<AirportDTO> getAirports() {
		return airportService.getList();
	}

	@RequestMapping(value = "/airports", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void addAirport(@RequestBody AirportDTO airportDTO) {
		airportService.save(airportDTO);
	}

	@RequestMapping(value = "/airports/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteAirport(@PathVariable String id) {
		airportService.delete(id);
	}

	@RequestMapping(value = "/airports", method = RequestMethod.PUT)
	public void updateAirport(@RequestBody AirportDTO airportDTO) {
		airportService.save(airportDTO);
	}
}
