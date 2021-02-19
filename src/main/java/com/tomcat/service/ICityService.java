package com.tomcat.service;

import java.util.List;

import com.tomcat.dto.CityDTO;
import com.tomcat.entity.City;

public interface ICityService {
	public List<CityDTO> getList();
	
	public CityDTO get(String id);
	
	public void save(CityDTO cityDTO);
	
	public void delete(String id);
	
	public List<City> getList1();
	
}
