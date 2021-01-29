package com.tomcat.dto;

import java.io.Serializable;
import java.util.List;

public class TaxDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int tax_Id;

	private String taxName;

	private List<TaxPriceDTO> taxPrices;

	private List<TicketDTO> tickets;

	public TaxDTO() {
	}

	public int getTax_Id() {
		return this.tax_Id;
	}

	public void setTax_Id(int tax_Id) {
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

	public TaxPriceDTO addTaxPrice(TaxPriceDTO taxPrice) {
		getTaxPrices().add(taxPrice);
		taxPrice.setTax(this);

		return taxPrice;
	}

	public TaxPriceDTO removeTaxPrice(TaxPriceDTO taxPrice) {
		getTaxPrices().remove(taxPrice);
		taxPrice.setTax(null);

		return taxPrice;
	}

	public List<TicketDTO> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<TicketDTO> tickets) {
		this.tickets = tickets;
	}

}