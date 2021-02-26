package com.tomcat.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer customer_Id;

	private String address;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT+07:00")
	private Date birthDay;

	private String firstName;

	private String identityNumber;

	private String lastName;

	private List<TicketDTO> tickets;

	public CustomerDTO() {
	}

	public Integer getCustomer_Id() {
		return this.customer_Id;
	}

	public void setCustomer_Id(Integer customer_Id) {
		this.customer_Id = customer_Id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthDay() {
		return this.birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getIdentityNumber() {
		return this.identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<TicketDTO> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<TicketDTO> tickets) {
		this.tickets = tickets;
	}

	public TicketDTO addTicket(TicketDTO ticket) {
		getTickets().add(ticket);
		ticket.setCustomer(this);

		return ticket;
	}

	public TicketDTO removeTicket(TicketDTO ticket) {
		getTickets().remove(ticket);
		ticket.setCustomer(null);

		return ticket;
	}

}