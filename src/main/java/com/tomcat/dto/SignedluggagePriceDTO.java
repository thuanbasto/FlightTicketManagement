package com.tomcat.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


public class SignedluggagePriceDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer price_Id;
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+07:00")
	private Date modifiedDate;

	private double price;
	
	private Integer signedLuggage_Id;

//	private SignedluggageDTO signedluggage;

	public SignedluggagePriceDTO() {
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

	public Integer getSignedLuggage_Id() {
		return signedLuggage_Id;
	}

	public void setSignedLuggage_Id(Integer signedLuggage_Id) {
		this.signedLuggage_Id = signedLuggage_Id;
	}

//	public SignedluggageDTO getSignedluggage() {
//		return this.signedluggage;
//	}
//
//	public void setSignedluggage(SignedluggageDTO signedluggage) {
//		this.signedluggage = signedluggage;
//	}

}