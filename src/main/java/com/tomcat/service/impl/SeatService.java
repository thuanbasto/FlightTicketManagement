package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomcat.converter.SeatConverter;
import com.tomcat.dto.SeatDTO;
import com.tomcat.entity.Seat;
import com.tomcat.repository.SeatRepository;
import com.tomcat.service.ISeatService;

@Service
public class SeatService implements ISeatService{
	
	@Autowired
	SeatConverter seatConverter;
	
	@Autowired
	SeatRepository seatRepository;

	@Override
	public List<SeatDTO> getSeats() {
		List<Object[]> objs = seatRepository.getSeats();
		List<SeatDTO> seatDTOs = new ArrayList<SeatDTO>();
		objs.forEach(seat -> seatDTOs.add(seatConverter.toDTO(seat)));
		return seatDTOs;
	}

	@Override
	public SeatDTO getSeat(String id) {
		Object[] obj = seatRepository.getSeat(id);
		if (obj.length == 0) return null;
		SeatDTO seatDTO = seatConverter.toDTO((Object[]) obj[0]);
		return seatDTO;
	}

	@Override
	public void save(SeatDTO seatDTO) {
		Seat seat = seatConverter.toSeat(seatDTO);
		seatRepository.save(seat);
	}

	@Override
	public void delete(String id) {
		seatRepository.delete(id);
	}
	
}
