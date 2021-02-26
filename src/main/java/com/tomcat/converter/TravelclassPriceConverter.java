package com.tomcat.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomcat.dto.TravelclassDTO;
import com.tomcat.dto.TravelclassPriceDTO;
import com.tomcat.entity.TravelclassPrice;

@Component
public class TravelclassPriceConverter {
	
	@Autowired
	TravelclassConverter travelclassConverter;
	
	public TravelclassPriceDTO toDTO(TravelclassPrice entity) {
		TravelclassPriceDTO travelclasspriceDTO = new TravelclassPriceDTO();
		travelclasspriceDTO.setPrice_Id(entity.getPrice_Id());
		travelclasspriceDTO.setModifiedDate(entity.getModifiedDate());
		travelclasspriceDTO.setPrice(entity.getPrice());
		TravelclassDTO travelclass =  travelclassConverter.toDTO(entity.getTravelclass());
		travelclasspriceDTO.setTravelclass(travelclass);
		return travelclasspriceDTO;
	}
	
	public TravelclassPrice toEntity(TravelclassPriceDTO travelclasspriceDTO) {
		TravelclassPrice result = new TravelclassPrice();
		result.setModifiedDate(travelclasspriceDTO.getModifiedDate());
		result.setPrice(travelclasspriceDTO.getPrice());
		result.setPrice_Id(travelclasspriceDTO.getPrice_Id());
		return result;
	}
	

}
