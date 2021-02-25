package com.tomcat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tomcat.entity.Airplane;

public interface AirplaneRepository extends JpaRepository<Airplane, String>{

}
