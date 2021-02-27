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
	ITravelClassService travelclassService;
	
	@GetMapping("/travelclasses")
	public ResponseEntity<List<TravelClassDTO>> getTravelclasss(){
		List<TravelClassDTO> travelclassDTOs = travelclassService.getTravelClasses();
		if(travelclassDTOs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else return new ResponseEntity<>(travelclassDTOs,HttpStatus.OK);
	}
	
	@GetMapping("/travelclasses/{id}")
	public ResponseEntity<TravelClassDTO> getTravelclass(@PathVariable("id") String id){
		TravelClassDTO travelclassDTO = travelclassService.getTravelClass(id);
		if(travelclassDTO != null) {
			return new ResponseEntity<>(travelclassDTO,HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/travelclasses")
	public ResponseEntity<TravelClassDTO> addTravelclass(@RequestBody TravelClassDTO travelclassDTO){
		travelclassService.save(travelclassDTO);
		return new ResponseEntity<>(travelclassDTO,HttpStatus.CREATED);
	}
	
	@PutMapping("/travelclasses/{id}")
	public ResponseEntity<TravelClassDTO> updateTravelclass(@RequestBody TravelClassDTO travelclassDTO, @PathVariable("id") String id){
		TravelClassDTO _travelclassDTO = travelclassService.getTravelClass(id);
		if(_travelclassDTO != null) {
			travelclassService.save(travelclassDTO);
			return new ResponseEntity<>(travelclassDTO,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	@DeleteMapping("/travelclasses/{id}")
	public ResponseEntity<HttpStatus> deleteTravelclass(@PathVariable String id) {
		try {
			travelclassService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
