package com.tomcat.dto;

import java.io.Serializable;

public class AirportDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String airport_Id;

	private String name;

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