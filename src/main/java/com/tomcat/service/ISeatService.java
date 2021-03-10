package com.tomcat.service;

import java.util.List;

import com.tomcat.dto.SeatDTO;

public interface ISeatService {

	public List<SeatDTO> getSeats();

	public SeatDTO getSeat(String id);

	public void save(SeatDTO seatDTO);

	public void delete(String id);
	
	public List<SeatDTO> getBookedSeats(String id);
}
