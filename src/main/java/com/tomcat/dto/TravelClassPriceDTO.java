package com.tomcat.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


public class TravelClassPriceDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer price_Id;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+07:00")
	private Date modifiedDate;
	
	private double price;
	
	private String name;

	private TravelClassDTO travelClass;

	private Integer travelClass_Id;

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

	public TravelClassPriceDTO() {
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

	public TravelClassDTO getTravelClass() {
		return this.travelClass;
	}

	public void setTravelclass(TravelClassDTO travelClass) {
		this.travelClass = travelClass;
	}

}