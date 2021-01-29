package com.tomcat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the airplane database table.
 * 
 */
@Entity
@NamedQuery(name="Airplane.findAll", query="SELECT a FROM Airplane a")
public class Airplane implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String airplane_Id;

	private String name;

	//bi-directional many-to-one association to Flight
	@OneToMany(mappedBy="airplane")
	private List<Flight> flights;

	public Airplane() {
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

	public List<Flight> getFlights() {
		return this.flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public Flight addFlight(Flight flight) {
		getFlights().add(flight);
		flight.setAirplane(this);

		return flight;
	}

	public Flight removeFlight(Flight flight) {
		getFlights().remove(flight);
		flight.setAirplane(null);

		return flight;
	}

}