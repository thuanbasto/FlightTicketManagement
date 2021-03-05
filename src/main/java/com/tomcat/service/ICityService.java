package com.tomcat.service;

import java.util.List;
import java.util.Set;

import com.tomcat.dto.CityDTO;

public interface ICityService {
	public List<CityDTO> getList();
	
	public CityDTO get(String id);
	
	public void save(CityDTO cityDTO);
	
	public void delete(String id);
	
	public Set<CityDTO> getCityDTOList();
	
}
