package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomcat.converter.CustomerConverter;
import com.tomcat.converter.TicketConverter;
import com.tomcat.dto.CustomerDTO;
import com.tomcat.dto.TicketDTO;
import com.tomcat.entity.Customer;
import com.tomcat.entity.Ticket;
import com.tomcat.repository.CustomerRepository;
import com.tomcat.repository.TicketRepository;
import com.tomcat.service.ICustomerService;
import com.tomcat.service.ITicketService;

@Service
public class TicketService implements ITicketService {

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	TicketConverter ticketConverter;
	
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

	@Override
//	@Transactional
	public TicketDTO save(TicketDTO ticketDTO) {
		Ticket ticket = ticketConverter.toEntity(ticketDTO);
		
		Customer customer = ticket.getCustomer();
		customerRepository.save(customer);
		ticket.setCustomer(customer);
		ticketRepository.save(ticket);
		
		CustomerDTO customerDTO = customerConverter.toDTO(customer);
		ticketDTO.setCustomer(customerDTO);
		ticketDTO.setTicket_Id(ticket.getTicket_Id());
		
		return ticketDTO;
	}

	@Override
	public void delete(Integer id) {
		ticketRepository.delete(id);
	}

}
