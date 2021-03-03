package com.tomcat.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


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
	private Integer tax_Id;

	private String taxName;

	//bi-directional many-to-one association to TaxPrice
	@OneToMany(mappedBy="tax" ,cascade = {CascadeType.REMOVE})
	private List<TaxPrice> taxPrices;

	//bi-directional many-to-many association to Ticket
	@ManyToMany(mappedBy="taxs")
	private Set<Ticket> tickets;

	public Tax() {
	}

	public Integer getTax_Id() {
		return this.tax_Id;
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

	public Set<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

}