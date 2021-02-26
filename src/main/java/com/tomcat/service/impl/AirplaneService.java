package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomcat.converter.AirplaneConverter;
import com.tomcat.dto.AirplaneDTO;
import com.tomcat.entity.Airplane;
import com.tomcat.repository.AirplaneRepository;
import com.tomcat.service.IAirplaneService;

@Service
public class AirplaneService implements IAirplaneService {

	@Autowired
	private AirplaneRepository airplaneRepository;

	@Autowired
	private AirplaneConverter airplaneConverter;

	@Override
	public List<AirplaneDTO> getAirplanes() {
		List<AirplaneDTO> airplaneDTOs = new ArrayList<AirplaneDTO>();
		List<Airplane> airplanes = airplaneRepository.findAll();
		airplanes.forEach(airplane -> airplaneDTOs.add(airplaneConverter.toAirplaneDTO(airplane)));
		return airplaneDTOs;
	}

	@Override
	public AirplaneDTO getAirplane(String id) {
		Airplane airplane = airplaneRepository.findOne(id);
		if(airplane == null) {
			return new AirplaneDTO();
		}
		return airplaneConverter.toAirplaneDTO(airplane);
	}

	@Override
	public void save(AirplaneDTO airplaneDTO) {
		Airplane airplane = airplaneConverter.toAirplane(airplaneDTO);
		airplaneRepository.save(airplane);
	}

	@Override
	public void delete(String id) {
		airplaneRepository.delete(id);
	}
}
