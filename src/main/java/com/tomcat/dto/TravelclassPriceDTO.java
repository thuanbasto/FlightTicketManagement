package com.tomcat.dto;

import java.io.Serializable;
import java.util.Date;


public class TravelclassPriceDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int price_Id;

	private Date modifiedDate;

	private double price;

	private TravelclassDTO travelclass;

	public TravelclassPriceDTO() {
	}

	public int getPrice_Id() {
		return this.price_Id;
	}

	public void setPrice_Id(int price_Id) {
		this.price_Id = price_Id;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public TravelclassDTO getTravelclass() {
		return this.travelclass;
	}

	public void setTravelclass(TravelclassDTO travelclass) {
		this.travelclass = travelclass;
	}

}