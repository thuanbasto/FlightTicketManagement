package com.tomcat.converter;

import org.springframework.stereotype.Component;

import com.tomcat.dto.TravelclassDTO;
import com.tomcat.entity.Travelclass;

@Component
public class TravelclassConverter {
	public TravelclassDTO toDTO(Travelclass entity) {
		TravelclassDTO travelclassDTO = new TravelclassDTO();
		travelclassDTO.setName(entity.getName());
		travelclassDTO.setQuantity(entity.getQuantity());
		travelclassDTO.setSeats(null);
		travelclassDTO.setTravelclassPrices(null);
		return travelclassDTO;
	}
	
	public Travelclass toEntity(TravelclassDTO travelclassDTO) {
		Travelclass entity = new Travelclass();
		entity.setTravelClass_Id(travelclassDTO.getTravelClass_Id());
		entity.setName(travelclassDTO.getName());
		entity.setQuantity(travelclassDTO.getQuantity());
		return entity;
	}
	
	public Travelclass toEntity(TravelclassDTO travelclassDTO, Travelclass entity) {
		entity.setTravelClass_Id(travelclassDTO.getTravelClass_Id());
		entity.setName(travelclassDTO.getName());
		entity.setQuantity(travelclassDTO.getQuantity());
		return entity;
	}
}
