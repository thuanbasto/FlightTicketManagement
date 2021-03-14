package com.tomcat.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomcat.dto.BookingDTO;
import com.tomcat.dto.TicketDTO;
import com.tomcat.dto.UserDTO;
import com.tomcat.entity.Booking;
import com.tomcat.entity.Ticket;

@Component
public class BookingConverter {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private TicketConverter ticketConverter;

	public Booking toBooking(BookingDTO bookingDTO) {
		return modelMapper.map(bookingDTO, Booking.class);
	}

	public BookingDTO toBookingDTO(Booking booking) {
		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setBooking_Id(booking.getBooking_Id());
		bookingDTO.setBookingDate(booking.getBookingDate());
		bookingDTO.setEmail(booking.getEmail());
		bookingDTO.setNumberOfTicket(booking.getTickets().size());
		bookingDTO.setPaymentMethod(booking.getPaymentMethod());
		bookingDTO.setPhone(booking.getPhone());

		if (booking.getUser() != null) {
			UserDTO userDTO = userConverter.toDTO(booking.getUser());
			userDTO.setBookings(null);
			userDTO.setRoles(null);
			bookingDTO.setUser(userDTO);
		}
		
		double totalPrice = 0;
		List<TicketDTO> ticketDTOs = new ArrayList<TicketDTO>();
			for(Ticket ticket : booking.getTickets()) {
			TicketDTO ticketDTO = ticketConverter.toDTO(ticket);
			ticketDTO.setBooking(null);
			totalPrice += ticketDTO.getTicket_PriceTotal(); // total booking price
			ticketDTOs.add(ticketDTO);
		}
		// set total price
		bookingDTO.setTickets(ticketDTOs);
		bookingDTO.setTotalPrice(totalPrice);

		return bookingDTO;
	}
	
	public BookingDTO toDTO (Object[] obj){
		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setBooking_Id(Integer.valueOf(String.valueOf(obj[0])));
		
		return bookingDTO;
	}
}
