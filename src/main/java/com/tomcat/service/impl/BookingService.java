package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tomcat.converter.BookingConverter;
import com.tomcat.dto.BookingDTO;
import com.tomcat.entity.Booking;
import com.tomcat.entity.Customer;
import com.tomcat.entity.Tax;
import com.tomcat.entity.Ticket;
import com.tomcat.entity.User;
import com.tomcat.repository.BookingRepository;
import com.tomcat.repository.CustomerRepository;
import com.tomcat.repository.TaxRepository;
import com.tomcat.repository.TicketRepository;
import com.tomcat.service.IBookingService;

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
	
	@Autowired
	private TaxRepository taxRepository;

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
		
		Set<Tax> taxes = new HashSet<Tax>(taxRepository.findAll());

		List<Ticket> tickets = new ArrayList<Ticket>();
		booking.getTickets().forEach(ticket -> {
			Customer customer = ticket.getCustomer();
			customerRepository.save(customer);

			ticket.setTaxs(taxes);
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

		bookingDTO.setBooking_Id(booking.getBooking_Id());
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

	@Override
	@Transactional
	public Double getBookingPriceInYear() {
		Calendar cal = Calendar.getInstance();
		String year = String.valueOf(cal.get(Calendar.YEAR));
		List<Object[]> objs = bookingRepository.findByBookingDateYear(year);
		Double totalBookingPrice = 0.0;
		if(objs == null)
		{
			return totalBookingPrice;
		}
		else 
		{
			List<BookingDTO> bookingDTOs = new ArrayList<BookingDTO>();
			for (Object[] item : objs) {
				BookingDTO _booking = bookingConverter.toDTO(item);
				if(_booking != null)
				{
					 Booking bookingEntity = bookingRepository.findOne(_booking.getBooking_Id());
					 BookingDTO bookingDTO = bookingConverter.toBookingDTO(bookingEntity);
					 bookingDTOs.add(bookingDTO);
				}
			}
			
			for (int i = 0; i < bookingDTOs.size(); i++) {
				totalBookingPrice += bookingDTOs.get(i).getTotalPrice();
			}
			return totalBookingPrice;
		}
		
	}

	@Override
	@Transactional
	public Double getBookingPriceInYearAndMonth() {
		Calendar cal = Calendar.getInstance();
		String month = String.valueOf(cal.get(Calendar.MONTH)+1);
		String year = String.valueOf(cal.get(Calendar.YEAR));
		List<Object[]> objs = bookingRepository.findByBookingDateMonth(year, month);
		Double totalBookingPrice = 0.0;
		if(objs == null)
		{
			return totalBookingPrice;
		}
		else 
		{
			List<BookingDTO> bookingDTOs = new ArrayList<BookingDTO>();
			for (Object[] item : objs) {
				BookingDTO _booking = bookingConverter.toDTO(item);
				if(_booking != null)
				{
					 Booking bookingEntity = bookingRepository.findOne(_booking.getBooking_Id());
					 BookingDTO bookingDTO = bookingConverter.toBookingDTO(bookingEntity);
					 bookingDTOs.add(bookingDTO);
				}
			}
			
			for (int i = 0; i < bookingDTOs.size(); i++) {
				totalBookingPrice += bookingDTOs.get(i).getTotalPrice();
			}
			return totalBookingPrice;
		}
		
	}
	
}
