package com.tomcat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the travelclass database table.
 * 
 */
@Entity
@NamedQuery(name="Travelclass.findAll", query="SELECT t FROM Travelclass t")
public class Travelclass implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer travelClass_Id;

	private String name;

	private Integer quantity;

	//bi-directional many-to-one association to Seat
	@OneToMany(mappedBy="travelclass")
	private List<Seat> seats;

	//bi-directional many-to-one association to TravelclassPrice
	@OneToMany(mappedBy="travelclass")
	private List<TravelclassPrice> travelclassPrices;

	public Travelclass() {
	}

	public Integer getTravelClass_Id() {
		return this.travelClass_Id;
	}

	public void setTravelClass_Id(Integer travelClass_Id) {
		this.travelClass_Id = travelClass_Id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<Seat> getSeats() {
		return this.seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public Seat addSeat(Seat seat) {
		getSeats().add(seat);
		seat.setTravelclass(this);

		return seat;
	}

	public Seat removeSeat(Seat seat) {
		getSeats().remove(seat);
		seat.setTravelclass(null);

		return seat;
	}

	public List<TravelclassPrice> getTravelclassPrices() {
		return this.travelclassPrices;
	}

	public void setTravelclassPrices(List<TravelclassPrice> travelclassPrices) {
		this.travelclassPrices = travelclassPrices;
	}

	public TravelclassPrice addTravelclassPrice(TravelclassPrice travelclassPrice) {
		getTravelclassPrices().add(travelclassPrice);
		travelclassPrice.setTravelclass(this);

		return travelclassPrice;
	}

	public TravelclassPrice removeTravelclassPrice(TravelclassPrice travelclassPrice) {
		getTravelclassPrices().remove(travelclassPrice);
		travelclassPrice.setTravelclass(null);

		return travelclassPrice;
	}

}