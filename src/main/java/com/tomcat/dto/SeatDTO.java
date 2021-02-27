package com.tomcat.dto;

import java.io.Serializable;
import java.util.List;

public class SeatDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String seat_Id;

	private TravelClassDTO travelClass;

	private List<TicketDTO> tickets;

	public SeatDTO() {
	}

	public String getSeat_Id() {
		return this.seat_Id;
	}

	public void setSeat_Id(String seat_Id) {
		this.seat_Id = seat_Id;
	}

	public TravelClassDTO getTravelClass() {
		return this.travelClass;
	}

	public void setTravelClass(TravelClassDTO travelClass) {
		this.travelClass = travelClass;
	}

	public List<TicketDTO> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<TicketDTO> tickets) {
		this.tickets = tickets;
	}

	public TicketDTO addTicket(TicketDTO ticket) {
		getTickets().add(ticket);
		ticket.setSeat(this);

		return ticket;
	}

	public TicketDTO removeTicket(TicketDTO ticket) {
		getTickets().remove(ticket);
		ticket.setSeat(null);

		return ticket;
	}

}