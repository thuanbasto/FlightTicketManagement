package com.tomcat.service;

import java.util.List;

import com.tomcat.dto.CityDTO;
import com.tomcat.dto.CustomerDTO;

public interface ICityService{
	public List<CityDTO> getList();
	public CityDTO getCityDTO(String id);
	
}
