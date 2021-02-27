package com.tomcat.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomcat.dto.SeatDTO;
import com.tomcat.dto.TravelClassDTO;
import com.tomcat.dto.TravelClassPriceDTO;
import com.tomcat.entity.Seat;

@Component
public class SeatConverter {
	
	@Autowired
	TravelClassConverter travelClassConverter;
	
	public Seat toSeat(SeatDTO seatDTO) {
		Seat seat = new Seat();
		seat.setSeat_Id(seatDTO.getSeat_Id());
		seat.setTravelclass(travelClassConverter.toEntity(seatDTO.getTravelClass()));
		
		return seat;
	}
	
	public SeatDTO toDTO(Seat seat){
		return new SeatDTO();
	}
	
	public SeatDTO toDTO(Object[] obj) {
		SeatDTO seatDTO = new SeatDTO();
		seatDTO.setSeat_Id(String.valueOf(obj[0]));
		
		TravelClassDTO travelclassDTO = new TravelClassDTO();
		travelclassDTO.setName(String.valueOf(obj[1]));
		travelclassDTO.setTravelClass_Id(Integer.valueOf(String.valueOf(obj[2])));
		travelclassDTO.setQuantity(Integer.valueOf(String.valueOf(obj[3])));
		
		TravelClassPriceDTO travelclassPriceDTO = new TravelClassPriceDTO();
		travelclassPriceDTO.setPrice_Id(Integer.valueOf(String.valueOf(obj[4])));
		travelclassPriceDTO.setPrice(Double.valueOf(String.valueOf(obj[5])));
		travelclassPriceDTO.setModifiedDate((Date) obj[6]);
		
		List<TravelClassPriceDTO> travelclassPriceDTOs = new ArrayList<TravelClassPriceDTO>();
		travelclassPriceDTOs.add(travelclassPriceDTO);
		
		travelclassDTO.setTravelClassPrices(travelclassPriceDTOs);
		seatDTO.setTravelClass(travelclassDTO);
		
		return seatDTO;
	}
}
