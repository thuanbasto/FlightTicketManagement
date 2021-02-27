package com.tomcat.service;

import java.util.List;

import com.tomcat.dto.TravelClassPriceDTO;

public interface ITravelClassPriceService {
	
	List<TravelClassPriceDTO> getTravelClassPrices();
	
	TravelClassPriceDTO getTravelClassPrice(String id);
	
	void save(TravelClassPriceDTO travelclasspriceDTO);
	
	void delete(String id);

}
