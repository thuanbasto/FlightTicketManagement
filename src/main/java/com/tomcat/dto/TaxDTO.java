package com.tomcat.dto;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class TaxDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer tax_Id;

	@NotEmpty(message = "Tax name must not be null or empty")
	private String taxName;

	private List<TaxPriceDTO> taxPrices;

	private List<TicketDTO> tickets;

	public TaxDTO() {
	}
	
	public void setTax_Id(Integer tax_Id) {
		this.tax_Id = tax_Id;
	}

	public String getTaxName() {
		return this.taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public List<TaxPriceDTO> getTaxPrices() {
		return this.taxPrices;
	}

	public void setTaxPrices(List<TaxPriceDTO> taxPrices) {
		this.taxPrices = taxPrices;
	}

	public Integer getTax_Id() {
		return tax_Id;
	}

	public List<TicketDTO> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<TicketDTO> ticketDTOs) {
		this.tickets = ticketDTOs;
	}
	
}