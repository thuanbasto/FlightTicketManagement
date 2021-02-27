package com.tomcat.converter;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomcat.dto.TravelClassDTO;
import com.tomcat.dto.TravelClassPriceDTO;
import com.tomcat.entity.Travelclass;
import com.tomcat.entity.TravelclassPrice;

@Component
public class TravelClassPriceConverter {
	
	@Autowired
	TravelClassConverter travelClassConverter;
	
	public TravelClassPriceDTO toDTO(TravelclassPrice travelClassPrice) {
		TravelClassPriceDTO travelClasspriceDTO = new TravelClassPriceDTO();
		travelClasspriceDTO.setPrice_Id(travelClassPrice.getPrice_Id());
		travelClasspriceDTO.setModifiedDate(travelClassPrice.getModifiedDate());
		travelClasspriceDTO.setPrice(travelClassPrice.getPrice());
		
		TravelClassDTO travelClassDTO = new TravelClassDTO();
		travelClassDTO.setTravelClass_Id(travelClassPrice.getTravelclass().getTravelClass_Id());
		
		travelClasspriceDTO.setTravelclass(travelClassDTO);
		
		return travelClasspriceDTO;
	}
	
	public TravelclassPrice toEntity(TravelClassPriceDTO travelClassPriceDTO) {
		TravelclassPrice travelClassPrice = new TravelclassPrice();
		travelClassPrice.setModifiedDate(new Date());
		travelClassPrice.setPrice(travelClassPriceDTO.getPrice());
		travelClassPrice.setPrice_Id(travelClassPriceDTO.getPrice_Id());
		
		Travelclass travelClass = new Travelclass();
		travelClass.setTravelClass_Id(travelClassPriceDTO.getTravelClass_Id());
		
		travelClassPrice.setTravelclass(travelClass);
		
		return travelClassPrice;
	}
	

}
