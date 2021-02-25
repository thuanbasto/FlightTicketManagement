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

import com.tomcat.dto.AirplaneDTO;
import com.tomcat.service.IAirplaneService;

@RestController
@RequestMapping("/api")
public class AirplaneAPI {
	
	@Autowired
	private IAirplaneService airplaneService;
	
	@GetMapping("/airplanes")
	public ResponseEntity<List<AirplaneDTO>> getAirplanes(){
		List<AirplaneDTO> airplaneDTOs = airplaneService.getAirplanes();
		if(airplaneDTOs.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(airplaneDTOs);
		}
	}
	
	@GetMapping("/airplanes/{id}")
	public ResponseEntity<AirplaneDTO> getAirplane(@PathVariable("id") String id){
		AirplaneDTO airplaneDTO = airplaneService.getAirplane(id);
		if(airplaneDTO.getAirplane_Id() == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(airplaneDTO);
	}
	
	@PostMapping("/airplanes")
	public ResponseEntity<AirplaneDTO> addAirplane(@RequestBody AirplaneDTO airplaneDTO){
		AirplaneDTO _airplanedDTO = airplaneService.getAirplane(airplaneDTO.getAirplane_Id());
		if(_airplanedDTO.getAirplane_Id() == null) {
			airplaneService.save(airplaneDTO);
			return ResponseEntity.ok(airplaneDTO);
		}
		return ResponseEntity.noContent().build();
	}
	

	@PutMapping("/airplanes/{id}")
	public ResponseEntity<AirplaneDTO> updateAirplane(@PathVariable("id") String id,
			@RequestBody AirplaneDTO airplaneDTO){
		AirplaneDTO _airplaneDTO = airplaneService.getAirplane(id);
		if(_airplaneDTO.getAirplane_Id() == null) {
			return ResponseEntity.notFound().build();
		}else {
			airplaneService.save(airplaneDTO);
			return ResponseEntity.ok(airplaneDTO);
		}
	}
	
	@DeleteMapping("/airplanes/{id}")
	public ResponseEntity<HttpStatus> deleteAirplane(@PathVariable("id") String id) {
		AirplaneDTO airplaneDTO = airplaneService.getAirplane(id);
		if(airplaneDTO.getAirplane_Id() != null) {
			airplaneService.delete(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	

}