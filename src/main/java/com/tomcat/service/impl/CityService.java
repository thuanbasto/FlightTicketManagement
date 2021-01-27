package com.tomcat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tomcat.entity.City;
import com.tomcat.repository.CityRepository;
import com.tomcat.service.ICityService;

@Service
@Transactional
public class CityService implements ICityService{

	@Autowired
	CityRepository cityRepository;
	
	@Override
	public List<City> getList() {
		return cityRepository.findAll();
	}
	
}
