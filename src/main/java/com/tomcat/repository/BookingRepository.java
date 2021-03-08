package com.tomcat.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tomcat.entity.Booking;
import com.tomcat.entity.User;

public interface BookingRepository extends JpaRepository<Booking, Integer>{
	List<Booking> findByBookingDateBetween(Date fromDate, Date toDate);
	List<Booking> findByUser(User user);
}
