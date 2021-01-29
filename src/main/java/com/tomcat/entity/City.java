package com.tomcat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String city_Id;

	private String name;

	//bi-directional many-to-one association to Airport
	@OneToMany(mappedBy="city")
	private List<Airport> airports;

	public City() {
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

	public List<Airport> getAirports() {
		return this.airports;
	}

	public void setAirports(List<Airport> airports) {
		this.airports = airports;
	}

	public Airport addAirport(Airport airport) {
		getAirports().add(airport);
		airport.setCity(this);

		return airport;
	}

	public Airport removeAirport(Airport airport) {
		getAirports().remove(airport);
		airport.setCity(null);

		return airport;
	}

}