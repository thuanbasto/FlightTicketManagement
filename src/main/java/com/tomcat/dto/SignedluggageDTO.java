package com.tomcat.dto;

import java.io.Serializable;
import java.util.List;

public class SignedluggageDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int signedLuggage_Id;

	private String name;

	private double weight;

	private List<SignedluggagePriceDTO> signedluggagePrices;

	private List<TicketDTO> tickets;

	public SignedluggageDTO() {
	}

	public int getSignedLuggage_Id() {
		return this.signedLuggage_Id;
	}

	public void setSignedLuggage_Id(int signedLuggage_Id) {
		this.signedLuggage_Id = signedLuggage_Id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public List<SignedluggagePriceDTO> getSignedluggagePrices() {
		return this.signedluggagePrices;
	}

	public void setSignedluggagePrices(List<SignedluggagePriceDTO> signedluggagePrices) {
		this.signedluggagePrices = signedluggagePrices;
	}

	public SignedluggagePriceDTO addSignedluggagePrice(SignedluggagePriceDTO signedluggagePrice) {
		getSignedluggagePrices().add(signedluggagePrice);
		signedluggagePrice.setSignedluggage(this);

		return signedluggagePrice;
	}

	public SignedluggagePriceDTO removeSignedluggagePrice(SignedluggagePriceDTO signedluggagePrice) {
		getSignedluggagePrices().remove(signedluggagePrice);
		signedluggagePrice.setSignedluggage(null);

		return signedluggagePrice;
	}

	public List<TicketDTO> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<TicketDTO> tickets) {
		this.tickets = tickets;
	}

	public TicketDTO addTicket(TicketDTO ticket) {
		getTickets().add(ticket);
		ticket.setSignedluggage(this);

		return ticket;
	}

	public TicketDTO removeTicket(TicketDTO ticket) {
		getTickets().remove(ticket);
		ticket.setSignedluggage(null);

		return ticket;
	}

}