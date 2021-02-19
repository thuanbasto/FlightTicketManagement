package com.tomcat.dto;

import java.io.Serializable;
import java.util.Date;


public class TaxPriceDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer tax_Price_Id;

	private Date modifiedDate;

	private String price;

	private TaxDTO tax;
	private String taxName;
	private int tax_Id;
	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public int getTax_Id() {
		return tax_Id;
	}

	public void setTax_Id(int tax_Id) {
		this.tax_Id = tax_Id;
	}

	

	public TaxPriceDTO() {
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