package com.tomcat.dto;

import java.io.Serializable;
import java.util.Date;


public class SignedluggagePriceDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer price_Id;

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