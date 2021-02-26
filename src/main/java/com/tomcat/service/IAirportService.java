package com.tomcat.service;

import java.util.List;

import com.tomcat.dto.AirportDTO;

public interface IAirportService {

	public List<AirportDTO> getList();

	public AirportDTO get(String id);

	public void save(AirportDTO airportDTO);

	public void delete(String id);
	
}
