package com.tomcat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tomcat.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer>{
}
