package com.tomcat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tomcat.entity.TaxPrice;

public interface TaxPriceRepository extends JpaRepository<TaxPrice, Integer> {

}
