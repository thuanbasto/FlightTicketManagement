package com.tomcat.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tomcat.entity.Booking;
import com.tomcat.entity.User;

public interface BookingRepository extends JpaRepository<Booking, Integer>{
	List<Booking> findByBookingDateBetween(Date fromDate, Date toDate);
	List<Booking> findByUser(User user);
	
	@Query(value="select booking.Booking_Id,PaymentMethod,User_Id,BookingDate,Phone,Email from booking,ticket where booking.Booking_Id = ticket.Booking_Id  and YEAR(BookingDate) = ?1 group by booking.Booking_Id,PaymentMethod,User_Id,BookingDate,Phone,Email ",nativeQuery=true)
	public List<Object[]> findByBookingDateYear(String year);
	
	@Query(value="select booking.Booking_Id,PaymentMethod,User_Id,BookingDate,Phone,Email from booking,ticket where booking.Booking_Id = ticket.Booking_Id  and YEAR(BookingDate) = ?1 and MONTH(BookingDate) = ?2 group by booking.Booking_Id,PaymentMethod,User_Id,BookingDate,Phone,Email",nativeQuery=true)
	public List<Object[]> findByBookingDateMonth(String year,String month);
}
