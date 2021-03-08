package com.tomcat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tomcat.entity.SignedluggagePrice;

@Repository
public interface SignedLuggagePriceRepository extends JpaRepository<SignedluggagePrice, Integer> {

}

