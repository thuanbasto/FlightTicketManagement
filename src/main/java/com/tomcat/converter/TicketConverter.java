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
import com.tomcat.entity.Tax;
import com.tomcat.entity.Ticket;

@Component
public class TicketConverter {

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	private CustomerConverter customerConverter;
	
	@Autowired
	private FlightConverter flightConverter;
	
	public TicketDTO toDTO(Ticket ticket) {

		TicketDTO ticketDTO = new TicketDTO();

		ticketDTO.setTicket_Id(ticket.getTicket_Id());
		ticketDTO.setTicket_PriceTotal(ticket.getTicket_PriceTotal());
		
		if(ticket.getBooking() != null) {
			BookingDTO bookingDTO = mapper.map(ticket.getBooking(), BookingDTO.class);
			if(bookingDTO.getUser() != null) {
				bookingDTO.getUser().setRoles(null);
			}
			bookingDTO.setTickets(null);
			ticketDTO.setBooking(bookingDTO); 
		}

		CustomerDTO customerDTO = customerConverter.toDTO(ticket.getCustomer());
		customerDTO.setTickets(null);
		ticketDTO.setCustomer(customerDTO);// set customer dto

		SeatDTO seatDTO = mapper.map(ticket.getSeat(), SeatDTO.class);
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

		SignedluggageDTO signedluggageDTO = mapper.map(ticket.getSignedluggage(), SignedluggageDTO.class);
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
			TaxDTO taxDTO = mapper.map(tax, TaxDTO.class);
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
		
		ticketDTO.setTicket_PriceTotal( travleClassPrice + signedLuggagePrice + taxPrice + flightPrice);
		
		return ticketDTO;
	}
	
	public Ticket toEntity(TicketDTO ticketDTO) {
		
		return mapper.map(ticketDTO, Ticket.class);
	}

}
