package com.tomcat.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.dto.TicketDTO;
import com.tomcat.service.ITicketService;

@RestController
@RequestMapping("/api")
public class TicketAPI {

	@Autowired
	ITicketService ticketService;

	@GetMapping(value = "/tickets", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<TicketDTO>> getTickets() {
		List<TicketDTO> ticketDTOs = ticketService.getList();
		if (ticketDTOs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(ticketDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/tickets/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<TicketDTO> getTicket(@PathVariable("id") Integer id) {
		TicketDTO ticketDTO = ticketService.getTicket(id);
		if (ticketDTO.getTicket_Id() == null) {
			System.out.println("nono");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(ticketDTO, HttpStatus.OK);
		}
	}

	
	@PreAuthorize("hasAnyRole('STAFF','ANONYMOUS')")
	@PostMapping(value = "/tickets", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<TicketDTO> addticket(@RequestBody TicketDTO ticketDTO) {
		ticketService.save(ticketDTO);
		return new ResponseEntity<>(ticketDTO, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyRole('STAFF','ANONYMOUS')")
	@PutMapping(value = "/tickets/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<TicketDTO> updateticket(@RequestBody TicketDTO ticketDTO, @PathVariable Integer id) {
		TicketDTO _ticketDTO = ticketService.getTicket(id);
		if (_ticketDTO.getTicket_Id() != null) {
			ticketService.save(ticketDTO);
			return new ResponseEntity<>(ticketDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PreAuthorize("hasAnyRole('STAFF','ANONYMOUS')")
	@DeleteMapping(value = "/tickets/{id}")
	public ResponseEntity<String> deleteTicket(@PathVariable("id") Integer id) {
		try {
			ticketService.delete(id);
			return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Kh�ng th�nh c�ng", HttpStatus.NOT_FOUND);
		}

	}

}
