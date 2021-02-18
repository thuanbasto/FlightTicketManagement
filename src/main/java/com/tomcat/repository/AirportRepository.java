package com.tomcat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tomcat.entity.Airport;

public interface AirportRepository extends JpaRepository<Airport, String>{

}
