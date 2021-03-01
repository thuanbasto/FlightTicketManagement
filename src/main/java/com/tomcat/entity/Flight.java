package com.tomcat.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the flight database table.
 * 
 */
@Entity
@NamedQuery(name="Flight.findAll", query="SELECT f FROM Flight f")
public class Flight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer flight_Id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date arrivalDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date departureDate;

	private double flight_Price;

	//bi-directional many-to-one association to Airplane
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Airplane_Id")
	private Airplane airplane;

	//bi-directional many-to-one association to Airport
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="From_Airport_Id")
	private Airport fromAirport;

	//bi-directional many-to-one association to Airport
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="To_Airport_Id")
	private Airport toAirport;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="flight")
	private List<Ticket> tickets;

	public Flight() {
	}

	public Integer getFlight_Id() {
		return this.flight_Id;
	}

	public void setFlight_Id(Integer flight_Id) {
		this.flight_Id = flight_Id;
	}

	public Date getArrivalDate() {
		return this.arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public double getFlight_Price() {
		return this.flight_Price;
	}

	public void setFlight_Price(double flight_Price) {
		this.flight_Price = flight_Price;
	}

	public Airplane getAirplane() {
		return this.airplane;
	}

	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}

	public Airport getFromAirport() {
		return fromAirport;
	}

	public void setFromAirport(Airport fromAirport) {
		this.fromAirport = fromAirport;
	}

	public Airport getToAirport() {
		return toAirport;
	}

	public void setToAirport(Airport toAirport) {
		this.toAirport = toAirport;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setFlight(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setFlight(null);

		return ticket;
	}

}