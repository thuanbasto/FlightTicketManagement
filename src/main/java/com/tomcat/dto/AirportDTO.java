package com.tomcat.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class AirportDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String airport_Id;

	private String name;

	private CityDTO city;

//	@JsonIgnore
	private List<FlightDTO> flights1;

//	@JsonIgnore
	private List<FlightDTO> flights2;

	public AirportDTO() {
	}

	public String getAirport_Id() {
		return this.airport_Id;
	}

	public void setAirport_Id(String airport_Id) {
		this.airport_Id = airport_Id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CityDTO getCity() {
		return this.city;
	}

	public void setCity(CityDTO city) {
		this.city = city;
	}

	public List<FlightDTO> getFlights1() {
		return this.flights1;
	}

	public void setFlights1(List<FlightDTO> flights1) {
		this.flights1 = flights1;
	}

	public FlightDTO addFlights1(FlightDTO flights1) {
		getFlights1().add(flights1);
		flights1.setAirport1(this);

		return flights1;
	}

	public FlightDTO removeFlights1(FlightDTO flights1) {
		getFlights1().remove(flights1);
		flights1.setAirport1(null);

		return flights1;
	}

	public List<FlightDTO> getFlights2() {
		return this.flights2;
	}

	public void setFlights2(List<FlightDTO> flights2) {
		this.flights2 = flights2;
	}

	public FlightDTO addFlights2(FlightDTO flights2) {
		getFlights2().add(flights2);
		flights2.setAirport2(this);

		return flights2;
	}

	public FlightDTO removeFlights2(FlightDTO flights2) {
		getFlights2().remove(flights2);
		flights2.setAirport2(null);

		return flights2;
	}

}