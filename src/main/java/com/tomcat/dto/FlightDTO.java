package com.tomcat.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class FlightDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int flight_Id;

	private Date arrivalDate;

	private Date deparutreDate;

	private double flight_Price;

	private AirplaneDTO airplane;

	private AirportDTO airport1;

	private AirportDTO airport2;

	private List<TicketDTO> tickets;

	public FlightDTO() {
	}

	public int getFlight_Id() {
		return this.flight_Id;
	}

	public void setFlight_Id(int flight_Id) {
		this.flight_Id = flight_Id;
	}

	public Date getArrivalDate() {
		return this.arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getDeparutreDate() {
		return this.deparutreDate;
	}

	public void setDeparutreDate(Date deparutreDate) {
		this.deparutreDate = deparutreDate;
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

	public AirportDTO getAirport1() {
		return this.airport1;
	}

	public void setAirport1(AirportDTO airport1) {
		this.airport1 = airport1;
	}

	public AirportDTO getAirport2() {
		return this.airport2;
	}

	public void setAirport2(AirportDTO airport2) {
		this.airport2 = airport2;
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

}