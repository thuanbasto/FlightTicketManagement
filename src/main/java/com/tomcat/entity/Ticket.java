package com.tomcat.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the ticket database table.
 * 
 */
@Entity
@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer ticket_Id;

	//bi-directional many-to-one association to Booking
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Booking_Id")
	private Booking booking;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Customer_Id")
	private Customer customer;

	//bi-directional many-to-one association to Flight
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Flight_Id")
	private Flight flight;

	//bi-directional many-to-one association to Seat
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Seat_Id")
	private Seat seat;

	//bi-directional many-to-one association to Signedluggage
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SignedLuggage_Id")
	private Signedluggage signedluggage;

	//bi-directional many-to-many association to Tax
	@ManyToMany
	@JoinTable(
		name="ticket_tax"
		, joinColumns={
			@JoinColumn(name="Ticket_Id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Tax_Id")
			}
		)
	private Set<Tax> taxs;

	public Ticket() {
	}

	public Integer getTicket_Id() {
		return this.ticket_Id;
	}

	public void setTicket_Id(Integer ticket_Id) {
		this.ticket_Id = ticket_Id;
	}

	public Booking getBooking() {
		return this.booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Flight getFlight() {
		return this.flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Seat getSeat() {
		return this.seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Signedluggage getSignedluggage() {
		return this.signedluggage;
	}

	public void setSignedluggage(Signedluggage signedluggage) {
		this.signedluggage = signedluggage;
	}

	public Set<Tax> getTaxs() {
		return this.taxs;
	}

	public void setTaxs(Set<Tax> taxs) {
		this.taxs = taxs;
	}

}