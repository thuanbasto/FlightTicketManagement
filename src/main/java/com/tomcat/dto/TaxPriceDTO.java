package com.tomcat.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


public class TaxPriceDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer tax_Price_Id;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+07:00")
	private Date modifiedDate;

	private double price;

	private TaxDTO taxDTO;
	
	private Integer tax_Id;
	
	public TaxPriceDTO() {
	}

	public Integer getTax_Price_Id() {
		return tax_Price_Id;
	}

	public void setTax_Price_Id(Integer tax_Price_Id) {
		this.tax_Price_Id = tax_Price_Id;
	}

	public Date getModifiedDate() {
		return modifiedDate;
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

	public TaxDTO getTax() {
		return taxDTO;
	}

	public void setTax(TaxDTO taxDTO) {
		this.taxDTO = taxDTO;
	}

	public Integer getTax_Id() {
		return tax_Id;
	}

	public void setTax_Id(Integer tax_Id) {
		this.tax_Id = tax_Id;
	}

	
	
}