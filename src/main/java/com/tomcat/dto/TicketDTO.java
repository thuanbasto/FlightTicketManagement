package com.tomcat.dto;

import java.io.Serializable;
import java.util.List;

public class TicketDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer ticket_Id;

	private double ticket_PriceTotal;

	private BookingDTO booking;

	private CustomerDTO customer;

	private FlightDTO flight;

	private SeatDTO seat;

	private SignedluggageDTO signedluggage;

	private List<TaxDTO> taxs;

	public TicketDTO() {
	}

	public Integer getTicket_Id() {
		return this.ticket_Id;
	}

	public void setTicket_Id(Integer ticket_Id) {
		this.ticket_Id = ticket_Id;
	}

	public double getTicket_PriceTotal() {
		return this.ticket_PriceTotal;
	}

	public void setTicket_PriceTotal(double ticket_PriceTotal) {
		this.ticket_PriceTotal = ticket_PriceTotal;
	}

	public BookingDTO getBooking() {
		return this.booking;
	}

	public void setBooking(BookingDTO booking) {
		this.booking = booking;
	}

	public CustomerDTO getCustomer() {
		return this.customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	public FlightDTO getFlight() {
		return this.flight;
	}

	public void setFlight(FlightDTO flight) {
		this.flight = flight;
	}

	public SeatDTO getSeat() {
		return this.seat;
	}

	public void setSeat(SeatDTO seat) {
		this.seat = seat;
	}

	public SignedluggageDTO getSignedluggage() {
		return this.signedluggage;
	}

	public void setSignedluggage(SignedluggageDTO signedluggage) {
		this.signedluggage = signedluggage;
	}

	public List<TaxDTO> getTaxs() {
		return this.taxs;
	}

	public void setTaxs(List<TaxDTO> taxs) {
		this.taxs = taxs;
	}
}