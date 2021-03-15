package com.tomcat.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class TravelClassDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer travelClass_Id;

	@NotEmpty(message = "Travle class name must not be empty")
	private String name;

	private Integer quantity;

	private List<SeatDTO> seats;

	private List<TravelClassPriceDTO> travelClassPrices;

	public TravelClassDTO() {
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

	public List<SeatDTO> getSeats() {
		return this.seats;
	}

	public void setSeats(List<SeatDTO> seats) {
		this.seats = seats;
	}

	public SeatDTO addSeat(SeatDTO seat) {
		getSeats().add(seat);
		seat.setTravelClass(this);

		return seat;
	}

	public SeatDTO removeSeat(SeatDTO seat) {
		getSeats().remove(seat);
		seat.setTravelClass(null);

		return seat;
	}

	public List<TravelClassPriceDTO> getTravelClassPrices() {
		return this.travelClassPrices;
	}

	public void setTravelClassPrices(List<TravelClassPriceDTO> travelClassPrices) {
		this.travelClassPrices = travelClassPrices;
	}

	public TravelClassPriceDTO addTravelclassPrice(TravelClassPriceDTO travelclassPrice) {
		getTravelClassPrices().add(travelclassPrice);
		travelclassPrice.setTravelclass(this);

		return travelclassPrice;
	}

	public TravelClassPriceDTO removeTravelclassPrice(TravelClassPriceDTO travelclassPrice) {
		getTravelClassPrices().remove(travelclassPrice);
		travelclassPrice.setTravelclass(null);

		return travelclassPrice;
	}

}