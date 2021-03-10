package com.tomcat.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tomcat.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer>{
	
	
	@Query(value="SELECT Flight_Id,From_Airport_Id,To_Airport_Id,DeparureDate,ArrivalDate,Flight_Price,Airplane_Id,Seat_Id , TravelClass_Id FROM flightticketmanagement.flight,flightticketmanagement.seat where flight.From_Airport_Id = (select airport.Airport_Id from flightticketmanagement.airport where airport.City_Id= ?1) and flight.To_Airport_Id = (select airport.Airport_Id from flightticketmanagement.airport where airport.City_Id= ?2) and DeparureDate = ?3 and Seat_Id  not in (select Seat_Id from flightticketmanagement.ticket) and Seat_Id = ?4",nativeQuery=true)
	public List<Object[]> findSeach1(String di,String den, String date,String hang);
	
	@Query(value="SELECT flight.*,s.Seat_Id, s.TravelClass_Id" + 
			" FROM flight,seat s" + 
			" where From_Airport_Id = (select Airport_Id from airport where airport.City_Id= ?1)" + 
			" and To_Airport_Id = (select Airport_Id from airport where airport.City_Id= ?2)" + 
			" and DepartureDate >= ?3" + 
			" and Seat_Id not in (select Seat_Id from ticket where flight.Flight_Id = Flight_Id)" + 
			" and ((select count(1) - (select count(1) from ticket,seat" + 
			" where ticket.seat_Id = seat.seat_Id and TravelClass_Id = s.TravelClass_Id" + 
			" and ticket.Flight_Id = flight.Flight_Id ) from seat where TravelClass_Id = s.TravelClass_Id) >= ?4)"
			,nativeQuery=true)
	public List<Object[]> searchFlight(String from,String to, Date departureDate,String number);
	
	@Query(value="SELECT flight.*,s.Seat_Id, s.TravelClass_Id" + 
			" FROM flight,seat s" + 
			" where flight.flight_Id = ?1" + 
			" and Seat_Id not in (select Seat_Id from ticket where flight.Flight_Id = Flight_Id)"
			,nativeQuery=true)
	public List<Object[]> getEmptySeatOfFlight(String id);
	
}
