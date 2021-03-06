package com.tomcat.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;


public class FlightDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer flight_Id;

	@Temporal(TemporalType.DATE)
//	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+07:00")
	private Date arrivalDate;

	@Temporal(TemporalType.DATE)
//	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+07:00")
	private Date departureDate;

	private double flight_Price;

	private AirplaneDTO airplane;

	private AirportDTO fromAirport;

	private AirportDTO toAirport;

	private List<TicketDTO> tickets;

	private Integer travelClass_Id;
	
	private List<Integer> listOfTravelClass_Id;
	
	public FlightDTO() {
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

	public AirplaneDTO getAirplane() {
		return this.airplane;
	}

	public void setAirplane(AirplaneDTO airplane) {
		this.airplane = airplane;
	}

	public AirportDTO getFromAirport() {
		return fromAirport;
	}

	public void setFromAirport(AirportDTO fromAirport) {
		this.fromAirport = fromAirport;
	}

	public AirportDTO getToAirport() {
		return toAirport;
	}

	public void setToAirport(AirportDTO toAirport) {
		this.toAirport = toAirport;
	}

	public List<TicketDTO> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<TicketDTO> tickets) {
		this.tickets = tickets;
	}

	public TicketDTO addTicket(TicketDTO ticket) {
		getTickets().add(ticket);
		ticket.setFlight(this);

		return ticket;
	}

	public TicketDTO removeTicket(TicketDTO ticket) {
		getTickets().remove(ticket);
		ticket.setFlight(null);

		return ticket;
	}

	public Integer getTravelClass_Id() {
		return travelClass_Id;
	}

	public void setTravelClass_Id(Integer travelClass_Id) {
		this.travelClass_Id = travelClass_Id;
	}

	public List<Integer> getListOfTravelClass_Id() {
		return listOfTravelClass_Id;
	}

	public void setListOfTravelClass_Id(List<Integer> listOfTravelClass_Id) {
		this.listOfTravelClass_Id = listOfTravelClass_Id;
	}

	
}