package com.tomcat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tomcat.dto.CityDTO;
import com.tomcat.repository.CityRepository;
import com.tomcat.service.ICityService;

@Service
@Transactional
public class CityService implements ICityService{

	@Autowired
	CityRepository cityRepository;

	@Override
	public CityDTO getCityDTO(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CityDTO> getList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
