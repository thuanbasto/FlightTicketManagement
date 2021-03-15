package com.tomcat.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.dto.SeatDTO;
import com.tomcat.service.ISeatService;

@RestController
@RequestMapping("/api")
public class SeatAPI {
	
	@Autowired
	ISeatService seatService;
	
	@GetMapping(value = "/seats")
	public ResponseEntity<List<SeatDTO>> getSeats(){
		List<SeatDTO> seatDTOs = seatService.getSeats();
		if (seatDTOs.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(seatDTOs,HttpStatus.OK);
	}
	
	@GetMapping(value = "/seats/flights/{id}")
	public ResponseEntity<List<SeatDTO>> getBookedSeats(@PathVariable("id") String id){
		List<SeatDTO> seatDTOs = seatService.getBookedSeats(id);
		if (seatDTOs == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(seatDTOs,HttpStatus.OK);
	}
	
	@GetMapping(value = "/seats/{id}")
	public ResponseEntity<SeatDTO> getSeat(@PathVariable("id") String id){
		SeatDTO seatDTO = seatService.getSeat(id);
		if(seatDTO == null) 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(seatDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/seats")
	public ResponseEntity<SeatDTO> addSeat(@RequestBody SeatDTO seatDTO){
		SeatDTO _seatDTO = seatService.getSeat(seatDTO.getSeat_Id());
		if(_seatDTO != null) 
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		seatService.save(seatDTO);
		return new ResponseEntity<>(seatDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/seats/{id}")
	public ResponseEntity<HttpStatus> deleteSeat(@PathVariable String id){
		seatService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/seats/{id}")
	public ResponseEntity<SeatDTO> updateSeat(@RequestBody SeatDTO seatDTO, @PathVariable("id") String id) {
		SeatDTO _seatDTO = seatService.getSeat(id);
		if(_seatDTO == null) 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		seatService.save(seatDTO);
		return new ResponseEntity<>(seatDTO, HttpStatus.OK);
	}
	
}
