package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tomcat.converter.BookingConverter;
import com.tomcat.dto.BookingDTO;
import com.tomcat.entity.Booking;
import com.tomcat.entity.User;
import com.tomcat.repository.BookingRepository;
import com.tomcat.service.IBookingService;

@Service
public class BookingService implements IBookingService{
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private BookingConverter bookingConverter;
	
	@Override
	@Transactional
	public List<BookingDTO> getBookings() {
		List<Booking> bookings = bookingRepository.findAll();
		List<BookingDTO> bookingDTOs = new ArrayList<BookingDTO>();
		bookings.forEach(booking ->{
			 BookingDTO bookingDTO = bookingConverter.toBookingDTO(booking);
			 bookingDTOs.add(bookingDTO);
		});
		return bookingDTOs;
	}
	
	@Override
	@Transactional
	public List<BookingDTO> getBookings(Date fromDate, Date toDate) {
		List<Booking> bookings = bookingRepository.findByBookingDateBetween(fromDate,toDate);
		List<BookingDTO> bookingDTOs = new ArrayList<BookingDTO>();
		bookings.forEach(booking ->{
			 BookingDTO bookingDTO = bookingConverter.toBookingDTO(booking);
			 bookingDTOs.add(bookingDTO);
		});
		return bookingDTOs;
	}

	@Override
	@Transactional
	public BookingDTO getBooking(Integer id) {
		Booking booking = bookingRepository.findOne(id);
		if(booking != null) {
			BookingDTO bookingDTO = bookingConverter.toBookingDTO(booking);
			return bookingDTO;
		}
		else return new BookingDTO();
	}

	@Override
	public void save(BookingDTO bookingDTO) {
		Booking booking = bookingConverter.toBooking(bookingDTO);
		bookingRepository.save(booking);
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
		bookings.forEach(booking ->{
			 BookingDTO bookingDTO = bookingConverter.toBookingDTO(booking);
			 bookingDTOs.add(bookingDTO);
		});
		return bookingDTOs;
	}

}
