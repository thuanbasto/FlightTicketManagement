package com.tomcat.service;

import java.util.List;

import com.tomcat.dto.TravelclassDTO;
import com.tomcat.dto.TravelclassPriceDTO;

public interface ITravelclassPriceService {
	List<TravelclassPriceDTO> getList();
	List<TravelclassDTO> getListTravelclass();
	TravelclassPriceDTO findbyid(int id);
	void save(TravelclassPriceDTO travelclasspriceDTO);
	void delete(int id);

}
