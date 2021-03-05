package com.tomcat.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SearchDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer flight_Id;

	@Temporal(TemporalType.DATE)
//	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+07:00")
	private Date arrivalDate;

	@Temporal(TemporalType.DATE)
//	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+07:00")
	private Date departureDate;

	private double flight_Price;

	private AirplaneDTO airplane;

	private AirportDTO fromAirport;

	private AirportDTO toAirport;
	
	private List<TaxDTO> tax;
	
	private String seat_Id;
	
	public String getSeat_Id() {
		return seat_Id;
	}

	public void setSeat_Id(String seat_Id) {
		this.seat_Id = seat_Id;
	}

	private TravelClassDTO travelClass;
	
	

	public SearchDTO() {
		super();
	}

	public Integer getFlight_Id() {
		return flight_Id;
	}

	public void setFlight_Id(Integer flight_Id) {
		this.flight_Id = flight_Id;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public double getFlight_Price() {
		return flight_Price;
	}

	public void setFlight_Price(double flight_Price) {
		this.flight_Price = flight_Price;
	}

	public AirplaneDTO getAirplane() {
		return airplane;
	}

	public void setAirplane(AirplaneDTO airplane) {
		this.airplane = airplane;
	}

	public AirportDTO getFromAirport() {
		return fromAirport;
	}

	public void setFromAirport(AirportDTO fromAirport) {
		this.fromAirport = fromAirport;
	}

	public AirportDTO getToAirport() {
		return toAirport;
	}

	public void setToAirport(AirportDTO toAirport) {
		this.toAirport = toAirport;
	}

	public List<TaxDTO> getTax() {
		return tax;
	}

	public void setTax(List<TaxDTO> tax) {
		this.tax = tax;
	}

	public TravelClassDTO getTravelClass() {
		return travelClass;
	}

	public void setTravelClass(TravelClassDTO travelClass) {
		this.travelClass = travelClass;
	}

}
