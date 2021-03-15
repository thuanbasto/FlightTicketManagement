package com.tomcat.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

public class AirplaneDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String airplane_Id;

	@NotEmpty(message = "Airplane name must not be null or empty")
	private String name;

	private List<FlightDTO> flights;

	public AirplaneDTO() {
	}

	public String getAirplane_Id() {
		return this.airplane_Id;
	}

	public void setAirplane_Id(String airplane_Id) {
		this.airplane_Id = airplane_Id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FlightDTO> getFlights() {
		return this.flights;
	}

	public void setFlights(List<FlightDTO> flights) {
		this.flights = flights;
	}

	public FlightDTO addFlight(FlightDTO flight) {
		getFlights().add(flight);
		flight.setAirplane(this);

		return flight;
	}

	public FlightDTO removeFlight(FlightDTO flight) {
		getFlights().remove(flight);
		flight.setAirplane(null);

		return flight;
	}

}