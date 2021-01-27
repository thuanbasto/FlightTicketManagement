package com.tomcat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tomcat.entity.City;

@Repository
@Transactional
public interface CityRepository extends JpaRepository<City, String>{
}
