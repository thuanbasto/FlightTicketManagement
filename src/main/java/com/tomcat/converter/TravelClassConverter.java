package com.tomcat.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tomcat.dto.TravelClassDTO;
import com.tomcat.dto.TravelClassPriceDTO;
import com.tomcat.entity.Travelclass;

@Component
public class TravelClassConverter {
	public TravelClassDTO toDTO(Object[] obj) {
		TravelClassDTO travelClassDTO = new TravelClassDTO();
		travelClassDTO.setName(String.valueOf(obj[0]));
		travelClassDTO.setTravelClass_Id(Integer.valueOf(String.valueOf(obj[1])));
		travelClassDTO.setQuantity(Integer.valueOf(String.valueOf(obj[2])));
		
		TravelClassPriceDTO travelclassPriceDTO = new TravelClassPriceDTO();
		travelclassPriceDTO.setPrice_Id(Integer.valueOf(String.valueOf(obj[3])));
		travelclassPriceDTO.setPrice(Double.valueOf(String.valueOf(obj[4])));
		travelclassPriceDTO.setModifiedDate((Date) obj[5]);
		
		List<TravelClassPriceDTO> travelclassPriceDTOs = new ArrayList<TravelClassPriceDTO>();
		travelclassPriceDTOs.add(travelclassPriceDTO);
		
		travelClassDTO.setTravelClassPrices(travelclassPriceDTOs);
		
		return travelClassDTO;
	}
	
	public Travelclass toEntity(TravelClassDTO travelClassDTO) {
		Travelclass travelClass = new Travelclass();
		travelClass.setTravelClass_Id(travelClassDTO.getTravelClass_Id());
		travelClass.setName(travelClassDTO.getName());
		travelClass.setQuantity(travelClassDTO.getQuantity());
		return travelClass;
	}
}
