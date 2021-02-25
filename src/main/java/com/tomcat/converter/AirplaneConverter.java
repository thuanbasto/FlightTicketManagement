package com.tomcat.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomcat.dto.AirplaneDTO;
import com.tomcat.entity.Airplane;

@Component
public class AirplaneConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public AirplaneDTO toAirplaneDTO(Airplane airplane) {
		AirplaneDTO airplaneDTO  = new AirplaneDTO();
		airplaneDTO.setAirplane_Id(airplane.getAirplane_Id());
		airplaneDTO.setName(airplane.getName());
		airplaneDTO.setFlights(null);
		
		return airplaneDTO;
	}
	
	public Airplane toAirplane(AirplaneDTO airplaneDTO) {
		return modelMapper.map(airplaneDTO, Airplane.class);
	}
}
