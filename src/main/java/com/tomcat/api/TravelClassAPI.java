package com.tomcat.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.dto.TravelClassDTO;
import com.tomcat.service.ITravelClassService;

@RestController
@RequestMapping("/api")
public class TravelClassAPI {
	
	@Autowired
	ITravelClassService travelClassService;
	
	@GetMapping("/travelclasses")
	public ResponseEntity<List<TravelClassDTO>> getTravelClasses(){
		List<TravelClassDTO> travelClassDTOs = travelClassService.getTravelClasses();
		if(travelClassDTOs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else return new ResponseEntity<>(travelClassDTOs,HttpStatus.OK);
	}
	
	@GetMapping("/travelclasses/{id}")
	public ResponseEntity<TravelClassDTO> getTravelClass(@PathVariable("id") String id){
		TravelClassDTO travelClassDTO = travelClassService.getTravelClass(id);
		if(travelClassDTO != null) {
			return new ResponseEntity<>(travelClassDTO,HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/travelclasses")
	public ResponseEntity<TravelClassDTO> addTravelClass(@RequestBody TravelClassDTO travelClassDTO){
		travelClassDTO = travelClassService.save(travelClassDTO);
		return new ResponseEntity<>(travelClassDTO,HttpStatus.CREATED);
	}
	
	@PutMapping("/travelclasses/{id}")
	public ResponseEntity<TravelClassDTO> updateTravelClass(@RequestBody TravelClassDTO travelClassDTO, @PathVariable("id") String id){
		TravelClassDTO _travelClassDTO = travelClassService.getTravelClass(id);
		if(_travelClassDTO != null) {
			travelClassService.save(travelClassDTO);
			return new ResponseEntity<>(travelClassDTO,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	@DeleteMapping("/travelclasses/{id}")
	public ResponseEntity<HttpStatus> deleteTravelClass(@PathVariable String id) {
		try {
			travelClassService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
