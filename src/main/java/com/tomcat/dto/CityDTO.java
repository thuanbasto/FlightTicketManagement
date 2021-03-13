package com.tomcat.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

public class CityDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "City ID must not be empty")
	private String city_Id;

	@NotEmpty(message = "City Name must not be empty")
	private String name;

//	@JsonIgnore
	private List<AirportDTO> airports;

	public CityDTO() {
	}

	public String getCity_Id() {
		return this.city_Id;
	}

	public void setCity_Id(String city_Id) {
		this.city_Id = city_Id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AirportDTO> getAirports() {
		return this.airports;
	}

	public void setAirports(List<AirportDTO> airports) {
		this.airports = airports;
	}

	public AirportDTO addAirport(AirportDTO airport) {
		getAirports().add(airport);
		airport.setCity(this);

		return airport;
	}

	public AirportDTO removeAirport(AirportDTO airport) {
		getAirports().remove(airport);
		airport.setCity(null);

		return airport;
	}

}