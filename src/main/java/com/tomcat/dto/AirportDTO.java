package com.tomcat.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;



public class AirportDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Airport Code must not be empty")
	private String airport_Id;

	@NotEmpty(message = "Airport Name must not be empty")
	private String name;

//	@NotEmpty(message = "Please select a City")
	private CityDTO city;

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

}