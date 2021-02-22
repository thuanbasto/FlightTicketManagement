package com.tomcat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the signedluggage database table.
 * 
 */
@Entity
@NamedQuery(name="Signedluggage.findAll", query="SELECT s FROM Signedluggage s")
public class Signedluggage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer signedLuggage_Id;

	private String name;

	private double weight;

	//bi-directional many-to-one association to SignedluggagePrice
	@OneToMany(mappedBy="signedluggage")
	private List<SignedluggagePrice> signedluggagePrices;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="signedluggage")
	private List<Ticket> tickets;

	public Signedluggage() {
	}

	public Integer getSignedLuggage_Id() {
		return this.signedLuggage_Id;
	}

	public void setSignedLuggage_Id(Integer signedLuggage_Id) {
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

	public List<SignedluggagePrice> getSignedluggagePrices() {
		return this.signedluggagePrices;
	}

	public void setSignedluggagePrices(List<SignedluggagePrice> signedluggagePrices) {
		this.signedluggagePrices = signedluggagePrices;
	}

	public SignedluggagePrice addSignedluggagePrice(SignedluggagePrice signedluggagePrice) {
		getSignedluggagePrices().add(signedluggagePrice);
		signedluggagePrice.setSignedluggage(this);

		return signedluggagePrice;
	}

	public SignedluggagePrice removeSignedluggagePrice(SignedluggagePrice signedluggagePrice) {
		getSignedluggagePrices().remove(signedluggagePrice);
		signedluggagePrice.setSignedluggage(null);

		return signedluggagePrice;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setSignedluggage(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setSignedluggage(null);

		return ticket;
	}

}