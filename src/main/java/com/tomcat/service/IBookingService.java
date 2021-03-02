package com.tomcat.service;

import java.util.List;

import com.tomcat.dto.BookingDTO;

public interface IBookingService {

	public List<BookingDTO> getBookings();
	
	public BookingDTO getBooking(Integer id);
	
	public void save(BookingDTO bookingDTO);
	
	public void delete(Integer id);
	
}
