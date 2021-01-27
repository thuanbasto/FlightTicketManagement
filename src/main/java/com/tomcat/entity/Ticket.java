package com.tomcat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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
	private int ticket_Id;

	private double ticket_PriceTotal;

	//bi-directional many-to-one association to Booking
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Booking_Id")
	private Booking booking;

	//bi-directional many-to-one association to Flight
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Flight_Id")
	private Flight flight;

	//bi-directional many-to-one association to Signedluggage
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SignedLuggage_Id")
	private Signedluggage signedluggage;

	//bi-directional many-to-one association to Seat
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Seat_Id")
	private Seat seat;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Customer_Id")
	private Customer customer;

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
	private List<Tax> taxs;

	public Ticket() {
	}

	public int getTicket_Id() {
		return this.ticket_Id;
	}

	public void setTicket_Id(int ticket_Id) {
		this.ticket_Id = ticket_Id;
	}

	public double getTicket_PriceTotal() {
		return this.ticket_PriceTotal;
	}

	public void setTicket_PriceTotal(double ticket_PriceTotal) {
		this.ticket_PriceTotal = ticket_PriceTotal;
	}

	public Booking getBooking() {
		return this.booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Flight getFlight() {
		return this.flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Signedluggage getSignedluggage() {
		return this.signedluggage;
	}

	public void setSignedluggage(Signedluggage signedluggage) {
		this.signedluggage = signedluggage;
	}

	public Seat getSeat() {
		return this.seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Tax> getTaxs() {
		return this.taxs;
	}

	public void setTaxs(List<Tax> taxs) {
		this.taxs = taxs;
	}

}