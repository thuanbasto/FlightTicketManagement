package com.tomcat.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the airport database table.
 * 
 */
@Entity
@NamedQuery(name="Airport.findAll", query="SELECT a FROM Airport a")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Airport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String airport_Id;

	private String name;

	//bi-directional many-to-one association to City
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="City_Id")
	private City city;

	//bi-directional many-to-one association to Flight
	@OneToMany(mappedBy="fromAirport", cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Flight> flights1;

	//bi-directional many-to-one association to Flight
	@OneToMany(mappedBy="toAirport", cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Flight> flights2;

	public Airport() {
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

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Flight> getFlights1() {
		return this.flights1;
	}

	public void setFlights1(List<Flight> flights1) {
		this.flights1 = flights1;
	}

	public Flight addFlights1(Flight flights1) {
		getFlights1().add(flights1);
		flights1.setFromAirport(this);

		return flights1;
	}

	public Flight removeFlights1(Flight flights1) {
		getFlights1().remove(flights1);
		flights1.setFromAirport(null);

		return flights1;
	}

	public List<Flight> getFlights2() {
		return this.flights2;
	}

	public void setFlights2(List<Flight> flights2) {
		this.flights2 = flights2;
	}

	public Flight addFlights2(Flight flights2) {
		getFlights2().add(flights2);
		flights2.setToAirport(this);

		return flights2;
	}

	public Flight removeFlights2(Flight flights2) {
		getFlights2().remove(flights2);
		flights2.setToAirport(null);

		return flights2;
	}

}