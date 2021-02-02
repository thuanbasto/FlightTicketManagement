package com.tomcat.dto;

import java.io.Serializable;
import java.util.List;

public class TravelclassDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int travelClass_Id;

	private String name;

	private int quantity;

	private List<SeatDTO> seats;

	private List<TravelclassPriceDTO> travelclassPrices;

	public TravelclassDTO() {
	}

	public int getTravelClass_Id() {
		return this.travelClass_Id;
	}

	public void setTravelClass_Id(int travelClass_Id) {
		this.travelClass_Id = travelClass_Id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<SeatDTO> getSeats() {
		return this.seats;
	}

	public void setSeats(List<SeatDTO> seats) {
		this.seats = seats;
	}

	public SeatDTO addSeat(SeatDTO seat) {
		getSeats().add(seat);
		seat.setTravelclass(this);

		return seat;
	}

	public SeatDTO removeSeat(SeatDTO seat) {
		getSeats().remove(seat);
		seat.setTravelclass(null);

		return seat;
	}

	public List<TravelclassPriceDTO> getTravelclassPrices() {
		return this.travelclassPrices;
	}

	public void setTravelclassPrices(List<TravelclassPriceDTO> travelclassPrices) {
		this.travelclassPrices = travelclassPrices;
	}

	public TravelclassPriceDTO addTravelclassPrice(TravelclassPriceDTO travelclassPrice) {
		getTravelclassPrices().add(travelclassPrice);
		travelclassPrice.setTravelclass(this);

		return travelclassPrice;
	}

	public TravelclassPriceDTO removeTravelclassPrice(TravelclassPriceDTO travelclassPrice) {
		getTravelclassPrices().remove(travelclassPrice);
		travelclassPrice.setTravelclass(null);

		return travelclassPrice;
	}

}