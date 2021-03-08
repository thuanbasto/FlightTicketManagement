package com.tomcat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tomcat.entity.Travelclass;

@Repository
public interface TravelClassRepository extends JpaRepository<Travelclass, Integer>{
	@Query(value="SELECT tc.name,tc.TravelClass_Id,quantity,price_Id,price,ModifiedDate FROM travelclass tc,travelclass_price tcp " + 
			"WHERE tc.TravelClass_Id = tcp.TravelClass_Id " + 
			"AND Price_Id = (SELECT Max(Price_Id) FROM travelclass_price WHERE tc.TravelClass_Id = TravelClass_Id)"
			,nativeQuery=true)
	List<Object[]> getTravelClasses();
	
	@Query(value="SELECT tc.name,tc.TravelClass_Id,quantity,price_Id,price,ModifiedDate FROM travelclass tc,travelclass_price tcp " + 
			" WHERE tc.TravelClass_Id = ?1 AND tc.TravelClass_Id = tcp.TravelClass_Id ORDER BY(Price_Id) desc Limit 1"
			,nativeQuery=true)
	Object[] getTravelClass(String id);
}
