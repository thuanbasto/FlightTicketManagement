package com.tomcat.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomcat.dto.BookingDTO;
import com.tomcat.dto.CustomerDTO;
import com.tomcat.dto.FlightDTO;
import com.tomcat.dto.SeatDTO;
import com.tomcat.dto.SignedluggageDTO;
import com.tomcat.dto.SignedluggagePriceDTO;
import com.tomcat.dto.TaxDTO;
import com.tomcat.dto.TaxPriceDTO;
import com.tomcat.dto.TicketDTO;
import com.tomcat.dto.TravelClassDTO;
import com.tomcat.dto.TravelClassPriceDTO;
import com.tomcat.dto.UserDTO;
import com.tomcat.entity.Booking;
import com.tomcat.entity.Tax;
import com.tomcat.entity.Ticket;

@Component
public class BookingConverter {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private CustomerConverter customerConverter;

	@Autowired
	private FlightConverter flightConverter;
	
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

}
