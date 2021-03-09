package com.tomcat.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomcat.dto.SignedluggagePriceDTO;
import com.tomcat.dto.TaxDTO;
import com.tomcat.dto.TaxPriceDTO;
import com.tomcat.dto.TicketDTO;
import com.tomcat.dto.TravelClassPriceDTO;
import com.tomcat.entity.Booking;
import com.tomcat.entity.Customer;
import com.tomcat.entity.Flight;
import com.tomcat.entity.Seat;
import com.tomcat.entity.Signedluggage;
import com.tomcat.entity.Tax;
import com.tomcat.entity.Ticket;

@Component
public class TicketConverter {

	@Autowired
	ModelMapper mapper;

	@Autowired
	private AirportConverter airportConverter;

	public TicketDTO toDTO(Ticket ticket) {

		TicketDTO ticketDTO = mapper.map(ticket, TicketDTO.class);

		if (ticketDTO.getBooking() != null) {
			ticketDTO.getBooking().setTickets(null);
			ticketDTO.getBooking().setUser(null);

		}

		if (ticketDTO.getCustomer() != null) {
			ticketDTO.getCustomer().setTickets(null);
		}

		if (ticketDTO.getSeat() != null) {
			ticketDTO.getSeat().setTickets(null);
			if (ticketDTO.getSeat().getTravelClass() != null) {
				ticketDTO.getSeat().getTravelClass().setSeats(null);
				List<TravelClassPriceDTO> travelClassPrices = new ArrayList<TravelClassPriceDTO>();
				travelClassPrices.addAll(ticketDTO.getSeat().getTravelClass().getTravelClassPrices());
				Collections.reverse(travelClassPrices);
				for (TravelClassPriceDTO travelClassPriceDTO : travelClassPrices) {
					if (travelClassPriceDTO.getModifiedDate().compareTo(ticketDTO.getBooking().getBookingDate()) < 0) {
						travelClassPriceDTO.setTravelclass(null);
						List<TravelClassPriceDTO> travelClassPrices1 = new ArrayList<TravelClassPriceDTO>();
						travelClassPrices1.add(travelClassPriceDTO);
						ticketDTO.getSeat().getTravelClass().setTravelClassPrices(travelClassPrices1);
						break;
					}
				}

			}
		}

		if (ticketDTO.getFlight() != null) {
			ticketDTO.getFlight().setTickets(null);
			if (ticketDTO.getFlight().getAirplane() != null) {
				ticketDTO.getFlight().getAirplane().setFlights(null);
			}
			if (ticketDTO.getFlight().getFromAirport() != null) {
				ticketDTO.getFlight()
						.setFromAirport(airportConverter.toAirportDTO(ticket.getFlight().getFromAirport()));
				ticketDTO.getFlight().getFromAirport().setCity(null);
			}
			if (ticketDTO.getFlight().getToAirport() != null) {
				ticketDTO.getFlight().setToAirport(airportConverter.toAirportDTO(ticket.getFlight().getToAirport()));
				ticketDTO.getFlight().getToAirport().setCity(null);
			}
		}

		if (ticketDTO.getTaxs() != null) {
			for (TaxDTO taxDTO : ticketDTO.getTaxs()) {
				taxDTO.setTickets(null);
				if (taxDTO.getTaxPrices() != null) {
					List<TaxPriceDTO> taxPriceDTOs = new ArrayList<TaxPriceDTO>();
					taxPriceDTOs.addAll(taxDTO.getTaxPrices());
					Collections.reverse(taxPriceDTOs);
					for (TaxPriceDTO taxPriceDTO : taxPriceDTOs) {
							taxPriceDTO.setTax(null);
						if (taxPriceDTO.getModifiedDate().compareTo(ticketDTO.getBooking().getBookingDate()) < 0) {
							List<TaxPriceDTO> taxPriceDTOs1 = new ArrayList<TaxPriceDTO>();
							taxPriceDTOs1.add(taxPriceDTO);
							taxDTO.setTaxPrices(taxPriceDTOs1);
							break;
						}
					}
				}
			}

		}

		if (ticketDTO.getSignedluggage() != null) {
			ticketDTO.getSignedluggage().setTickets(null);
			if (ticketDTO.getSignedluggage().getSignedluggagePrices() != null) {
				List<SignedluggagePriceDTO> signedluggagePriceDTOs = new ArrayList<SignedluggagePriceDTO>();
				signedluggagePriceDTOs.addAll(ticketDTO.getSignedluggage().getSignedluggagePrices());
				Collections.reverse(signedluggagePriceDTOs);
				for (SignedluggagePriceDTO signedluggagePriceDTO : signedluggagePriceDTOs) {
					if (signedluggagePriceDTO.getModifiedDate().compareTo(ticketDTO.getBooking().getBookingDate()) < 0) {
						List<SignedluggagePriceDTO> signedluggagePriceDTOs1 = new ArrayList<SignedluggagePriceDTO>();
						signedluggagePriceDTOs1.add(signedluggagePriceDTO);
						ticketDTO.getSignedluggage().setSignedluggagePrices(signedluggagePriceDTOs1);
						break;
					}
				}

			}
		}
		
		double flightPrice = ticketDTO.getFlight().getFlight_Price();
		
		double seatPrice = ticketDTO.getSeat().getTravelClass().getTravelClassPrices().get(0).getPrice();
		
		double signedluggagePrice = ticketDTO.getSignedluggage().getSignedluggagePrices().get(0).getPrice();
		
		
		double taxPrice = 0;
		
		for(TaxDTO tax : ticketDTO.getTaxs()) {
			if(tax.getTaxPrices().isEmpty()) {
				taxPrice = 0;
			}else {
				taxPrice += tax.getTaxPrices().get(0).getPrice();
			}
		}
		
		ticketDTO.setTicket_PriceTotal(flightPrice + seatPrice + signedluggagePrice + taxPrice);

		return ticketDTO;
	}
	
	public Ticket toEntity(TicketDTO ticketDTO) {
		Ticket ticket = new Ticket();
		
		if(ticketDTO.getTicket_Id() != null) {
			ticket.setTicket_Id(ticketDTO.getTicket_Id());
		}
		
		Customer customer = new Customer();
		customer.setCustomer_Id(ticketDTO.getCustomer_Id());
		
		Booking booking = new Booking();
		if(ticketDTO.getBooking_Id() == null) {
			ticket.setBooking(null);
		}else {
			booking.setBooking_Id(ticketDTO.getBooking_Id());
			ticket.setBooking(booking);
		}
		
		Flight flight = new Flight();
		flight.setFlight_Id(ticketDTO.getFlight_Id());
		
		Seat seat = new Seat();
		seat.setSeat_Id(ticketDTO.getSeat_Id());
		
		Signedluggage signedluggage = new Signedluggage();
		signedluggage.setSignedLuggage_Id(ticketDTO.getSignedLuggage_Id());
		
		Set<Tax> taxs = new LinkedHashSet<Tax>();
		
		for(Integer integer : ticketDTO.getTax_Id()) {
			Tax tax = new Tax();
			tax.setTax_Id(integer);
			taxs.add(tax);
		}
		

		ticket.setCustomer(customer);
		ticket.setFlight(flight);
		ticket.setSeat(seat);
		ticket.setSignedluggage(signedluggage);
		ticket.setTaxs(taxs);
		return ticket;
	}
	
	public Ticket toEntity(TicketDTO ticketDTO, Ticket ticket) {

		ticket = mapper.map(ticketDTO, Ticket.class);

		return ticket;
	}

}
