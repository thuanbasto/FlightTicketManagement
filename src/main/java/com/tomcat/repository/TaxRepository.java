package com.tomcat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tomcat.entity.Tax;


public interface TaxRepository extends JpaRepository<Tax, Integer> {
	Tax findByTaxName(String taxName);
}
