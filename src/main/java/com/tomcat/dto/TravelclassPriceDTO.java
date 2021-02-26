package com.tomcat.dto;

import java.io.Serializable;
import java.util.Date;


public class TravelclassPriceDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer price_Id;

	private Date modifiedDate;

	private double price;
	private Integer travelClass_Id;

	private String name;

	public Integer getTravelClass_Id() {
		return travelClass_Id;
	}

	public void setTravelClass_Id(Integer travelClass_Id) {
		this.travelClass_Id = travelClass_Id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private TravelclassDTO travelclass;

	public TravelclassPriceDTO() {
	}

	public Integer getPrice_Id() {
		return this.price_Id;
	}

	public void setPrice_Id(Integer price_Id) {
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