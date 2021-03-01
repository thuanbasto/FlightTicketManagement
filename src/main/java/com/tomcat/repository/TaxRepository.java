package com.tomcat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tomcat.entity.Tax;


public interface TaxRepository extends JpaRepository<Tax, Integer> {
	Tax findByTaxName(String taxName);
	
	@Query(value="SELECT t.Tax_Id,t.TaxName,tp.Tax_Price_Id,tp.Price,tp.ModifiedDate FROM tax t,tax_price tp WHERE t.Tax_Id = tp.Tax_Id AND " + 
			"Tax_Price_Id = (SELECT Max(Tax_price_Id) FROM tax_price WHERE t.Tax_Id = Tax_Id)"
			,nativeQuery=true)
	List<Object[]> getTaxes();
	
	@Query(value="SELECT t.Tax_Id,t.TaxName,tp.Tax_Price_Id,tp.Price,tp.ModifiedDate FROM tax t,tax_price tp "
			+ "WHERE t.Tax_Id = tp.Tax_Id AND t.Tax_Id = ?1 "
			+ "ORDER BY(Tax_Price_Id) DESC Limit 1"
			,nativeQuery=true)
	Object[] getTax(Integer id);
}
