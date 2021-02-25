package com.tomcat.service;

import java.util.List;

import com.tomcat.dto.AirplaneDTO;

public interface IAirplaneService {

	public List<AirplaneDTO> getAirplanes();

	public AirplaneDTO getAirplane(String id);

	public void save(AirplaneDTO airplaneDTO);

	public void delete(String id);
}
