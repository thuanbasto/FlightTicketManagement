package com.tomcat.service;

import java.util.Date;
import java.util.List;

import com.tomcat.dto.BookingDTO;

public interface IBookingService {

	public List<BookingDTO> getBookings();
	
	public List<BookingDTO> getBookings(String user_Id);
	
	public List<BookingDTO> getBookings(Date fromDate, Date toDate);
	
	public BookingDTO getBooking(Integer id);
	
	public void save(BookingDTO bookingDTO);
	
	public void delete(Integer id);
	
	public Double getBookingPriceInYear();
	
	public Double getBookingPriceInYearAndMonth();
	
	
}
