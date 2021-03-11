package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomcat.converter.CustomerConverter;
import com.tomcat.converter.TicketConverter;
import com.tomcat.dto.TaxDTO;
import com.tomcat.dto.TicketDTO;
import com.tomcat.entity.Customer;
import com.tomcat.entity.Tax;
import com.tomcat.entity.Ticket;
import com.tomcat.repository.CustomerRepository;
import com.tomcat.repository.TaxRepository;
import com.tomcat.repository.TicketRepository;
import com.tomcat.service.ITaxService;
import com.tomcat.service.ITicketService;

@Service
public class TicketService implements ITicketService {

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	TicketConverter ticketConverter;
	
	@Autowired
	ITaxService taxService;
	
	@Autowired
	TaxRepository taxRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerConverter customerConverter;

	@Transactional
	@Override
	public List<TicketDTO> getList() {
		List<TicketDTO> ticketDTOs = new ArrayList<TicketDTO>();
		List<Ticket> tickets = ticketRepository.findAll();

		for (Ticket ticket : tickets) {
			TicketDTO ticketDTO = ticketConverter.toDTO(ticket);
			ticketDTOs.add(ticketDTO);
		}

		return ticketDTOs;

	}

	@Transactional
	@Override
	public TicketDTO getTicket(Integer id) {
		Ticket ticket = ticketRepository.findOne(id);
		if (ticket != null) {
			TicketDTO ticketDTO = ticketConverter.toDTO(ticket);
			return ticketDTO;
		}
		TicketDTO ticketDTO = new TicketDTO();
		return ticketDTO;

	}

	@SuppressWarnings("unchecked")
	@Override
//	@Transactional
	public void save(TicketDTO ticketDTO) {
		
//		List<TaxDTO> taxes = taxService.getTaxes();
//		ticketDTO.setTaxs(taxes);
		Ticket ticket = ticketConverter.toEntity(ticketDTO);
		List<Tax> taxes = taxRepository.findAll();
		ticket.setTaxs((Set<Tax>) taxes);
		
		Customer customer = ticket.getCustomer();
		customerRepository.save(customer);
		ticket.setCustomer(customer);
		
		ticketRepository.save(ticket);
		
	}

	@Override
	public void delete(Integer id) {
		ticketRepository.delete(id);
	}

}
