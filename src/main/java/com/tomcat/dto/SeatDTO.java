package com.tomcat.dto;

import java.io.Serializable;
import java.util.List;

public class SeatDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String seat_Id;

	private TravelclassDTO travelclass;

	private List<TicketDTO> tickets;

	public SeatDTO() {
	}

	public String getSeat_Id() {
		return this.seat_Id;
	}

	public void setSeat_Id(String seat_Id) {
		this.seat_Id = seat_Id;
	}

	public TravelclassDTO getTravelclass() {
		return this.travelclass;
	}

	public void setTravelclass(TravelclassDTO travelclass) {
		this.travelclass = travelclass;
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