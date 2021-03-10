package com.tomcat.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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

	
	
	public Booking toBooking(BookingDTO bookingDTO) {
		return modelMapper.map(bookingDTO, Booking.class);
		
		
	}

	public BookingDTO toBookingDTO(Booking booking) {
		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setBooking_Id(booking.getBooking_Id());
		bookingDTO.setBookingDate(booking.getBookingDate());
		bookingDTO.setEmail(booking.getEmail());
		bookingDTO.setNumberOfTicket(booking.getTickets().size());
		bookingDTO.setTickets(this.toTicketDTOs(booking.getTickets()));
		bookingDTO.setPaymentMethod(booking.getPaymentMethod());
		bookingDTO.setPhone(booking.getPhone());

		if (booking.getUser() != null) {
			UserDTO userDTO = userConverter.toDTO(booking.getUser());
			userDTO.setBookings(null);
			userDTO.setRoles(null);
			bookingDTO.setUser(userDTO);
		}
		// set total price
		double totalPrice = 0;
		for (Ticket ticket : booking.getTickets()) {
			totalPrice += ticket.getTicket_PriceTotal();
		}
		bookingDTO.setTotalPrice(totalPrice);

		return bookingDTO;
	}

	// convert ticket list of 1 booking
	public List<TicketDTO> toTicketDTOs(List<Ticket> tickets) {

		List<TicketDTO> ticketDTOs = new ArrayList<TicketDTO>();
		tickets.forEach(ticket -> {
			TicketDTO ticketDTO = new TicketDTO();

			ticketDTO.setTicket_Id(ticket.getTicket_Id());
			ticketDTO.setTicket_PriceTotal(ticket.getTicket_PriceTotal());
			ticketDTO.setBooking(null); // set null booking

			CustomerDTO customerDTO = customerConverter.toDTO(ticket.getCustomer());
			ticketDTO.setCustomer(customerDTO);// set customer dto

			SeatDTO seatDTO = modelMapper.map(ticket.getSeat(), SeatDTO.class);
			TravelClassDTO travelClassDTO = seatDTO.getTravelClass();
			List<TravelClassPriceDTO> travelclassPriceDTOs = travelClassDTO.getTravelClassPrices();

			// price
			if (!travelclassPriceDTOs.isEmpty()) {
				TravelClassPriceDTO travelClassPriceDTO = travelclassPriceDTOs.stream()
						.max(Comparator.comparing(TravelClassPriceDTO::getModifiedDate)).get();
				travelClassPriceDTO.setTravelclass(null);
				travelClassDTO.setTravelClassPrices(Arrays.asList(travelClassPriceDTO));
			}
			travelClassDTO.setSeats(null);
			seatDTO.setTravelClass(travelClassDTO);

			seatDTO.setTickets(null);
			ticketDTO.setSeat(seatDTO); // set seat dto

			SignedluggageDTO signedluggageDTO = modelMapper.map(ticket.getSignedluggage(), SignedluggageDTO.class);

			// price
			if(!signedluggageDTO.getSignedluggagePrices().isEmpty()) {
				SignedluggagePriceDTO signedluggagePriceDTO = Collections.max(signedluggageDTO.getSignedluggagePrices(),
						Comparator.comparing(SignedluggagePriceDTO::getModifiedDate));
				signedluggageDTO.setSignedluggagePrices(Arrays.asList(signedluggagePriceDTO));
			}
			signedluggageDTO.setTickets(null);
			ticketDTO.setSignedluggage(signedluggageDTO); // set signed luggage dto

			FlightDTO flightDTO = flightConverter.toFlightDTO(ticket.getFlight());
			flightDTO.setTickets(null);
			ticketDTO.setFlight(flightDTO); // set flight dto

			// tax
			Set<Tax> taxs = ticket.getTaxs();
			List<TaxDTO> taxDTOs = new ArrayList<>();
			taxs.forEach(tax -> {
				tax.setTickets(null);
				TaxDTO taxDTO = modelMapper.map(tax, TaxDTO.class);
				List<TaxPriceDTO> taxPriceDTOs = taxDTO.getTaxPrices();
				if (!taxPriceDTOs.isEmpty()) {
					TaxPriceDTO taxPriceDTO = Collections.max(taxDTO.getTaxPrices(),
							Comparator.comparing(TaxPriceDTO::getModifiedDate));
					taxPriceDTO.setTax(null);
					taxDTO.setTaxPrices(Arrays.asList(taxPriceDTO));
				}
				taxDTOs.add(taxDTO);
			});
			ticketDTO.setTaxs(taxDTOs);
			ticketDTOs.add(ticketDTO);
		});
		return ticketDTOs;
	}

}
