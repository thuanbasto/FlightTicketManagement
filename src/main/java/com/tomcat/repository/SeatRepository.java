package com.tomcat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tomcat.entity.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, String>{
	@Query(value="SELECT Seat_Id,tc.name,tc.TravelClass_Id,quantity,price_Id,price,ModifiedDate FROM seat s,travelclass tc,travelclass_price tcp " + 
			"WHERE s.TravelClass_Id = tc.TravelClass_Id AND tc.TravelClass_Id = tcp.TravelClass_Id " + 
			"AND Price_Id = (SELECT Max(Price_Id) FROM travelclass_price WHERE tc.TravelClass_Id = TravelClass_Id)"
			,nativeQuery=true)
	List<Object[]> getSeats();
	
	// truyen tham so seat_Id vao ?1
	@Query(value="SELECT Seat_Id,tc.name,tc.TravelClass_Id,quantity,price_Id,price,ModifiedDate FROM seat s,travelclass tc,travelclass_price tcp " + 
			"WHERE s.Seat_Id = ?1 AND s.TravelClass_Id = tc.TravelClass_Id AND tc.TravelClass_Id = tcp.TravelClass_Id ORDER BY(Price_Id) desc Limit 1"
			,nativeQuery=true)
	Object[] getSeat(String seat_Id);
}
