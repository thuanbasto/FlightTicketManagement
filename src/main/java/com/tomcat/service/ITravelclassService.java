package com.tomcat.service;

import java.util.List;

import com.tomcat.dto.TravelclassDTO;

public interface ITravelclassService {
	List<TravelclassDTO> getList();
	TravelclassDTO findbyid(int id);
	void save(TravelclassDTO travelclassDTO);
	void delete(int id);
	

}
