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
		for (TicketDTO ticketDTO : bookingDTO.getTickets()) {
			totalPrice += ticketDTO.getTicket_PriceTotal();
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
			

			CustomerDTO customerDTO = customerConverter.toDTO(ticket.getCustomer());
			customerDTO.setTickets(null);
			ticketDTO.setCustomer(customerDTO);// set customer dto

			SeatDTO seatDTO = modelMapper.map(ticket.getSeat(), SeatDTO.class);
			TravelClassDTO travelClassDTO = seatDTO.getTravelClass();
			List<TravelClassPriceDTO> travelclassPriceDTOs = travelClassDTO.getTravelClassPrices();

			double travleClassPrice = 0;
			double signedLuggagePrice=0;
			double taxPrice = 0 ;
			
			// travel class price
			if (!travelclassPriceDTOs.isEmpty() && ticket.getBooking() !=null) {
				travelclassPriceDTOs.sort((obj1, obj2) -> obj1.getModifiedDate().compareTo(obj2.getModifiedDate()) * -1);
				Optional<TravelClassPriceDTO> travelClassPriceDTO = travelclassPriceDTOs.stream()
						.filter(price -> ticket.getBooking().getBookingDate().compareTo(price.getModifiedDate()) > 0)
						.findFirst();
				
				
				
				if (travelClassPriceDTO.isPresent() ) {
					TravelClassPriceDTO _travelClassPriceDTO = travelClassPriceDTO.get();
					travleClassPrice = _travelClassPriceDTO.getPrice(); // travle class price
					_travelClassPriceDTO.setTravelclass(null);
					travelClassDTO.setTravelClassPrices(Arrays.asList(_travelClassPriceDTO));
				} else {
					travelClassDTO.setTravelClassPrices(null);
				}
			}else {
				travelClassDTO.setTravelClassPrices(null);
			}
			travelClassDTO.setSeats(null);
			seatDTO.setTravelClass(travelClassDTO);

			seatDTO.setTickets(null);
			ticketDTO.setSeat(seatDTO); // set seat dto

			SignedluggageDTO signedluggageDTO = modelMapper.map(ticket.getSignedluggage(), SignedluggageDTO.class);
			List<SignedluggagePriceDTO> signedluggagePriceDTOs = signedluggageDTO.getSignedluggagePrices();
			// signed luggage price
			if (!signedluggagePriceDTOs.isEmpty() && ticket.getBooking() !=null) {
				signedluggagePriceDTOs.sort((obj1, obj2) -> obj1.getModifiedDate().compareTo(obj2.getModifiedDate()) * -1);
				Optional<SignedluggagePriceDTO> signedluggagePriceDTO = signedluggagePriceDTOs.stream()
						.filter(price -> ticket.getBooking().getBookingDate().compareTo(price.getModifiedDate()) > 0)
						.findFirst();
				if (signedluggagePriceDTO.isPresent()) {
					SignedluggagePriceDTO _signedluggagePriceDTO = signedluggagePriceDTO.get();
					signedLuggagePrice = _signedluggagePriceDTO.getPrice();
					signedluggageDTO.setSignedluggagePrices(Arrays.asList(_signedluggagePriceDTO));
				} else {
					signedluggageDTO.setSignedluggagePrices(null);
				}
			}else {
				signedluggageDTO.setSignedluggagePrices(null);
			}
			signedluggageDTO.setTickets(null);
			ticketDTO.setSignedluggage(signedluggageDTO); // set signed luggage dto

			FlightDTO flightDTO = flightConverter.toFlightDTO(ticket.getFlight());
			flightDTO.setTickets(null);
			ticketDTO.setFlight(flightDTO); // set flight dto
			double flightPrice = flightDTO.getFlight_Price();

			// tax
			Set<Tax> taxes = ticket.getTaxs();
			List<TaxDTO> taxDTOs = new ArrayList<>();
			
			for(Tax tax : taxes){
				tax.setTickets(null);
				TaxDTO taxDTO = modelMapper.map(tax, TaxDTO.class);
				List<TaxPriceDTO> taxPriceDTOs = taxDTO.getTaxPrices();
				if (!taxPriceDTOs.isEmpty() && ticket.getBooking() !=null) {
					taxPriceDTOs.sort((obj1, obj2) -> obj1.getModifiedDate().compareTo(obj2.getModifiedDate()) * -1);
					Optional<TaxPriceDTO> taxPriceDTO = taxPriceDTOs.stream()
							.filter(price -> ticket.getBooking().getBookingDate().compareTo(price.getModifiedDate()) > 0)
							.findFirst();
					
					if (taxPriceDTO.isPresent()) {
						TaxPriceDTO _taxPriceDTO = taxPriceDTO.get();
						
						 taxPrice += _taxPriceDTO.getPrice();
						
						_taxPriceDTO.setTax(null);
						taxDTO.setTaxPrices(Arrays.asList(_taxPriceDTO));
					} else {
						taxDTO.setTaxPrices(null);
					}
				}else {
					taxDTO.setTaxPrices(null);
				}
				taxDTOs.add(taxDTO);
			}
			ticketDTO.setTaxs(taxDTOs);
			ticketDTO.setBooking(null);
			
			ticketDTO.setTicket_PriceTotal( travleClassPrice + signedLuggagePrice + taxPrice + flightPrice);
			ticketDTOs.add(ticketDTO);
		});
		return ticketDTOs;
	}

}
