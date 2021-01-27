package com.tomcat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tax database table.
 * 
 */
@Entity
@NamedQuery(name="Tax.findAll", query="SELECT t FROM Tax t")
public class Tax implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tax_Id;

	private String taxName;

	//bi-directional many-to-one association to TaxPrice
	@OneToMany(mappedBy="tax")
	private List<TaxPrice> taxPrices;

	//bi-directional many-to-many association to Ticket
	@ManyToMany(mappedBy="taxs")
	private List<Ticket> tickets;

	public Tax() {
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

	public List<TaxPrice> getTaxPrices() {
		return this.taxPrices;
	}

	public void setTaxPrices(List<TaxPrice> taxPrices) {
		this.taxPrices = taxPrices;
	}

	public TaxPrice addTaxPrice(TaxPrice taxPrice) {
		getTaxPrices().add(taxPrice);
		taxPrice.setTax(this);

		return taxPrice;
	}

	public TaxPrice removeTaxPrice(TaxPrice taxPrice) {
		getTaxPrices().remove(taxPrice);
		taxPrice.setTax(null);

		return taxPrice;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

}