package com.tomcat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tomcat.entity.Signedluggage;

@Repository
public interface SignedLuggageRepository extends JpaRepository<Signedluggage, Integer> {
		 

	/*
	 * @Query("SELECT s FROM Signedluggage s inner join s.signedluggagePrices p WHERE s.signedLuggage_Id = :id"
	 * ) Signedluggage find(@Param("id") Integer id);
	 */

}
