package com.tomcat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tomcat.entity.Signedluggage;

@Repository
public interface SignedLuggageRepository extends JpaRepository<Signedluggage, Integer> {
		
	@Query(value="SELECT s.SignedLuggage_Id, s.Name, s.Weight, sp.Price_Id, sp.Price, sp.ModifiedDate "
			+ "FROM signedluggage s, signedluggage_price sp "
			+ "WHERE s.SignedLuggage_Id = sp.SignedLuggage_Id "
			+ "AND Price_Id = (SELECT Max(Price_Id) FROM signedluggage_price WHERE  SignedLuggage_Id = s.SignedLuggage_Id)"
			,nativeQuery=true)
	
	List<Object[]> getSignedLuggages();
	
	@Query(value="SELECT s.SignedLuggage_Id, s.Name, s.Weight, sp.Price_Id, sp.Price, sp.ModifiedDate "
			+ "FROM signedluggage s, signedluggage_price sp "
			+ "WHERE s.SignedLuggage_Id = sp.SignedLuggage_Id "
			+ "AND s.SignedLuggage_Id = ?1 ORDER BY(Price_Id) DESC Limit 1"
			,nativeQuery=true)
	
	Object[] getSignedLuggage(Integer id);


}
