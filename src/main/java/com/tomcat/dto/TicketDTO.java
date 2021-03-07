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
	
	private Integer customer_Id;
	private Integer booking_Id;
	private Integer flight_Id;
	private String seat_Id;
	private Integer signedLuggage_Id;
	private Integer[] tax_Id;


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

	public Integer getCustomer_Id() {
		return customer_Id;
	}

	public void setCustomer_Id(Integer customer_Id) {
		this.customer_Id = customer_Id;
	}


	public Integer getBooking_Id() {
		return booking_Id;
	}

	public void setBooking_Id(Integer booking_Id) {
		this.booking_Id = booking_Id;
	}

	public Integer getFlight_Id() {
		return flight_Id;
	}

	public void setFlight_Id(Integer flight_Id) {
		this.flight_Id = flight_Id;
	}

	public String getSeat_Id() {
		return seat_Id;
	}

	public void setSeat_Id(String seat_Id) {
		this.seat_Id = seat_Id;
	}

	public Integer getSignedLuggage_Id() {
		return signedLuggage_Id;
	}

	public void setSignedLuggage_Id(Integer signedLuggage_Id) {
		this.signedLuggage_Id = signedLuggage_Id;
	}

	public Integer[] getTax_Id() {
		return tax_Id;
	}

	public void setTax_Id(Integer[] tax_Id) {
		this.tax_Id = tax_Id;
	}

	

}