package com.tomcat.service;

import java.util.List;

import com.tomcat.dto.TravelClassDTO;

public interface ITravelClassService {

	List<TravelClassDTO> getTravelClasses();
	
	TravelClassDTO getTravelClass(String id);
	
	TravelClassDTO save(TravelClassDTO travelclassDTO);
	
	void delete(String id);
}
