package com.tomcat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the signedluggage_price database table.
 * 
 */
@Entity
@Table(name="signedluggage_price")
@NamedQuery(name="SignedluggagePrice.findAll", query="SELECT s FROM SignedluggagePrice s")
public class SignedluggagePrice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer price_Id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	private double price;

	//bi-directional many-to-one association to Signedluggage
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SignedLuggage_Id")
	private Signedluggage signedluggage;

	public SignedluggagePrice() {
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

	public Signedluggage getSignedluggage() {
		return this.signedluggage;
	}

	public void setSignedluggage(Signedluggage signedluggage) {
		this.signedluggage = signedluggage;
	}

}