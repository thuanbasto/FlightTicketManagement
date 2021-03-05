package com.tomcat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tomcat.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer>{
	@Query(value="SELECT Flight_Id,From_Airport_Id,To_Airport_Id,DeparureDate,ArrivalDate,Flight_Price,Airplane_Id FROM flightticketmanagement.flight where flight.From_Airport_Id = (select airport.Airport_Id from flightticketmanagement.airport where airport.City_Id= ?1)",nativeQuery=true)
	public List<Object[]> findSeach(String key);
	
	@Query(value="SELECT Flight_Id,From_Airport_Id,To_Airport_Id,DeparureDate,ArrivalDate,Flight_Price,Airplane_Id,Seat_Id , TravelClass_Id FROM flightticketmanagement.flight,flightticketmanagement.seat where flight.From_Airport_Id = (select airport.Airport_Id from flightticketmanagement.airport where airport.City_Id= ?1) and flight.To_Airport_Id = (select airport.Airport_Id from flightticketmanagement.airport where airport.City_Id= ?2) and DeparureDate = ?3 and Seat_Id  not in (select Seat_Id from flightticketmanagement.ticket) and Seat_Id = ?4",nativeQuery=true)
	public List<Object[]> findSeach1(String di,String den, String date,String hang);
	
}
