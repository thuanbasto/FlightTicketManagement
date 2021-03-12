package com.tomcat.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tomcat.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer>{
	
	
	@Query(value="SELECT flight.*,s.Seat_Id, s.TravelClass_Id" + 
			"	FROM flight,seat s" + 
			"		where flight.Flight_Id = ?1 and Seat_Id not in (select Seat_Id from ticket where flight.Flight_Id = Flight_Id)",nativeQuery=true)
	public List<Object[]> searchFlightSeat(String Flight_Id);
	
	@Query(value="SELECT flight.*" + 
			" FROM flight" + 
			" where From_Airport_Id = ?1" + 
			" and To_Airport_Id = ?2" + 
			" and DepartureDate >= ?3" + 
			" and ((select count(1) - (select count(1) from ticket,seat where ticket.seat_Id = seat.seat_Id" + 
			" and ticket.Flight_Id = flight.Flight_Id ) from seat) >= ?4)"
			,nativeQuery=true)
	public List<Object[]> searchFlight(String from,String to, Date departureDate,String number);
	
	@Query(value="SELECT flight.*,s.Seat_Id, s.TravelClass_Id" + 
			" FROM flight,seat s" + 
			" where flight.flight_Id = ?1" + 
			" and Seat_Id not in (select Seat_Id from ticket where flight.Flight_Id = Flight_Id)"
			,nativeQuery=true)
	public List<Object[]> getEmptySeatOfFlight(String id);
	
}
