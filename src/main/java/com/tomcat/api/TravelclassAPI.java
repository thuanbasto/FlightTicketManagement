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
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.dto.TravelclassDTO;
import com.tomcat.service.ITravelclassService;

@RestController
public class TravelclassAPI {
	
	@Autowired
	ITravelclassService travelclassService;
	
	@GetMapping("api/travelclass")
	public ResponseEntity<List<TravelclassDTO>> getTravelclasss(){
		List<TravelclassDTO> travelclassDTOs = travelclassService.getList();
		if(travelclassDTOs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else return new ResponseEntity<>(travelclassDTOs,HttpStatus.OK);
	}
	
	@GetMapping("api/travelclass/{id}")
	public ResponseEntity<TravelclassDTO> getTravelclass(@PathVariable("id") Integer id){
		TravelclassDTO travelclassDTO = travelclassService.findbyid(id);
		if(travelclassDTO != null) {
			return new ResponseEntity<>(travelclassDTO,HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("api/travelclass")
	public ResponseEntity<TravelclassDTO> addTravelclass(@RequestBody TravelclassDTO travelclassDTO){
		travelclassService.save(travelclassDTO);
		return new ResponseEntity<>(travelclassDTO,HttpStatus.CREATED);
	}
	
	@PutMapping("api/travelclass/{id}")
	public ResponseEntity<TravelclassDTO> updateTravelclass(@RequestBody TravelclassDTO travelclassDTO, @PathVariable("id") Integer id){
		TravelclassDTO _travelclassDTO = travelclassService.findbyid(id);
		if(_travelclassDTO != null) {
			travelclassService.save(travelclassDTO);
			return new ResponseEntity<>(travelclassDTO,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	@DeleteMapping("api/travelclass/{id}")
	public ResponseEntity<HttpStatus> deleteTravelclass(@PathVariable int id) {
		try {
			travelclassService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
