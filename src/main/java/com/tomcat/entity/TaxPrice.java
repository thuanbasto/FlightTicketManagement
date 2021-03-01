package com.tomcat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tax_price database table.
 * 
 */
@Entity
@Table(name="tax_price")
@NamedQuery(name="TaxPrice.findAll", query="SELECT t FROM TaxPrice t")
public class TaxPrice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer tax_Price_Id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	
	private double price;

	//bi-directional many-to-one association to Tax
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Tax_Id")
	private Tax tax;

	public TaxPrice() {
	}

	public Integer getTax_Price_Id() {
		return this.tax_Price_Id;
	}

	public void setTax_Price_Id(Integer tax_Price_Id) {
		this.tax_Price_Id = tax_Price_Id;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Tax getTax() {
		return this.tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}

}