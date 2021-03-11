package com.tomcat.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tomcat.converter.BookingConverter;
import com.tomcat.dto.BookingDTO;
import com.tomcat.dto.TicketDTO;
import com.tomcat.entity.Booking;
import com.tomcat.entity.Customer;
import com.tomcat.entity.Ticket;
import com.tomcat.entity.User;
import com.tomcat.repository.BookingRepository;
import com.tomcat.repository.CustomerRepository;
import com.tomcat.repository.TicketRepository;
import com.tomcat.service.IBookingService;
import com.tomcat.service.ITicketService;

@Service
public class BookingService implements IBookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BookingConverter bookingConverter;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Override
	@Transactional
	public List<BookingDTO> getBookings() {
		List<Booking> bookings = bookingRepository.findAll();
		List<BookingDTO> bookingDTOs = new ArrayList<BookingDTO>();
		bookings.forEach(booking -> {
			BookingDTO bookingDTO = bookingConverter.toBookingDTO(booking);
			bookingDTOs.add(bookingDTO);
		});
		return bookingDTOs;
	}

	@Override
	@Transactional
	public List<BookingDTO> getBookings(Date fromDate, Date toDate) {
		List<Booking> bookings = bookingRepository.findByBookingDateBetween(fromDate, toDate);
		List<BookingDTO> bookingDTOs = new ArrayList<BookingDTO>();
		bookings.forEach(booking -> {
			BookingDTO bookingDTO = bookingConverter.toBookingDTO(booking);
			bookingDTOs.add(bookingDTO);
		});
		return bookingDTOs;
	}

	@Override
	@Transactional
	public BookingDTO getBooking(Integer id) {
		Booking booking = bookingRepository.findOne(id);
		if (booking != null) {
			BookingDTO bookingDTO = bookingConverter.toBookingDTO(booking);
			return bookingDTO;
		} else
			return new BookingDTO();
	}

	@Override
	@Transactional
	public void save(BookingDTO bookingDTO) {
		
		Date date = new Date();

		Booking booking = bookingConverter.toBooking(bookingDTO);

		List<Ticket> tickets = new ArrayList<Ticket>();
		booking.getTickets().forEach(ticket -> {
			Customer customer = ticket.getCustomer();
			customerRepository.save(customer);

			ticket.setCustomer(customer);
			ticketRepository.save(ticket);
			tickets.add(ticket);
		});
		booking.setTickets(null);
		booking.setBookingDate(date);
		
		bookingRepository.save(booking);

		// update booking id in ticket
		tickets.forEach(ticket -> {
			ticket.setBooking(booking);
			ticketRepository.save(ticket);
		});

	}

	@Override
	public void delete(Integer id) {
		bookingRepository.delete(id);
	}

	@Override
	@Transactional
	public List<BookingDTO> getBookings(String user_Id) {
		List<Booking> bookings = bookingRepository.findByUser(new User(Integer.valueOf(user_Id)));
		List<BookingDTO> bookingDTOs = new ArrayList<BookingDTO>();
		bookings.forEach(booking -> {
			BookingDTO bookingDTO = bookingConverter.toBookingDTO(booking);
			bookingDTOs.add(bookingDTO);
		});
		return bookingDTOs;
	}

}
