package com.tomcat.repository;

import java.sql.ResultSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tomcat.dto.TaxPriceDTO;
import com.tomcat.entity.TaxPrice;

@Repository
public interface TaxPriceRepository extends JpaRepository<TaxPrice, Integer>{
	@Query(value = "SELECT tax_Price_Id,modifiedDate,price,tp.Tax_Id,taxName FROM flightticketmanagement.tax_price tp,flightticketmanagement.tax t where tp.Tax_Id = t.Tax_Id", nativeQuery = true)
	ResultSet myCustomQuery();

}
