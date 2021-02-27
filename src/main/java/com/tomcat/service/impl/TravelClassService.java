package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomcat.converter.TravelClassConverter;
import com.tomcat.dto.TravelClassDTO;
import com.tomcat.entity.Travelclass;
import com.tomcat.repository.TravelClassRepository;
import com.tomcat.service.ITravelClassService;

@Service
public class TravelClassService implements ITravelClassService{
	@Autowired
	TravelClassRepository travelClassRepository;
	
	@Autowired
	TravelClassConverter travelClassConverter;

	@Override
	public List<TravelClassDTO> getTravelClasses() {
		List<Object[]> objs = travelClassRepository.getTravelClasses();
		List<TravelClassDTO> travelClassDTOs = new ArrayList<TravelClassDTO>();
		objs.forEach(travelClass -> travelClassDTOs.add(travelClassConverter.toDTO(travelClass)));
		return travelClassDTOs;
	}

	@Override
	public TravelClassDTO getTravelClass(String id) {
		Object[] obj = travelClassRepository.getTravelClass(id);
		if (obj.length == 0) return null;
		TravelClassDTO travelClassDTO = travelClassConverter.toDTO((Object[]) obj[0]);
		return travelClassDTO;
	}

	@Override
	public void save(TravelClassDTO travelClassDTO) {
		Travelclass entity = travelClassConverter.toEntity(travelClassDTO);
		travelClassRepository.save(entity);
		
	}

	@Override
	public void delete(String id) {
		travelClassRepository.delete(Integer.valueOf(id));
	}

}
