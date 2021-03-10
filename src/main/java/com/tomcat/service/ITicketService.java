package com.tomcat.service;

import java.util.List;

import com.tomcat.dto.TicketDTO;

public interface ITicketService {
	public List<TicketDTO> getList();

	TicketDTO save(TicketDTO dto);

	TicketDTO getTicket(Integer id);
	
	void delete(Integer id);

}
