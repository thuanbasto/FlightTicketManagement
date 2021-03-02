package com.tomcat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tomcat.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{

}
