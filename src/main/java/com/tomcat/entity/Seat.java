package com.tomcat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the seat database table.
 * 
 */
@Entity
@NamedQuery(name="Seat.findAll", query="SELECT s FROM Seat s")
public class Seat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String seat_Id;

	//bi-directional many-to-one association to Travelclass
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TravelClass_Id")
	private Travelclass travelclass;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="seat")
	private List<Ticket> tickets;

	public Seat() {
	}

	public String getSeat_Id() {
		return this.seat_Id;
	}

	public void setSeat_Id(String seat_Id) {
		this.seat_Id = seat_Id;
	}

	public Travelclass getTravelclass() {
		return this.travelclass;
	}

	public void setTravelclass(Travelclass travelclass) {
		this.travelclass = travelclass;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setSeat(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setSeat(null);

		return ticket;
	}

}