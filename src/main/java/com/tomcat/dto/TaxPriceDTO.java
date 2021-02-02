package com.tomcat.dto;

import java.io.Serializable;
import java.util.Date;


public class TaxPriceDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int tax_Price_Id;

	private Date modifiedDate;

	private String price;

	private TaxDTO tax;

	public TaxPriceDTO() {
	}

	public int getTax_Price_Id() {
		return this.tax_Price_Id;
	}

	public void setTax_Price_Id(int tax_Price_Id) {
		this.tax_Price_Id = tax_Price_Id;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public TaxDTO getTax() {
		return this.tax;
	}

	public void setTax(TaxDTO tax) {
		this.tax = tax;
	}

}