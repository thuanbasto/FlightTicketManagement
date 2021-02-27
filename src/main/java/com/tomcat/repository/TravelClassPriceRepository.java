package com.tomcat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tomcat.entity.TravelclassPrice;

@Repository
public interface TravelClassPriceRepository extends JpaRepository<TravelclassPrice, Integer>{

}
