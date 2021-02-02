package com.tomcat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the travelclass_price database table.
 * 
 */
@Entity
@Table(name="travelclass_price")
@NamedQuery(name="TravelclassPrice.findAll", query="SELECT t FROM TravelclassPrice t")
public class TravelclassPrice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer price_Id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	private double price;

	//bi-directional many-to-one association to Travelclass
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TravelClass_Id")
	private Travelclass travelclass;

	public TravelclassPrice() {
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

	public Travelclass getTravelclass() {
		return this.travelclass;
	}

	public void setTravelclass(Travelclass travelclass) {
		this.travelclass = travelclass;
	}

}