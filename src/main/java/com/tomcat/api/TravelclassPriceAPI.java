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

import com.tomcat.dto.TravelclassPriceDTO;
import com.tomcat.service.ITravelclassPriceService;

@RestController
public class TravelclassPriceAPI {
	@Autowired
	ITravelclassPriceService travelclassPriceService;
	
	@GetMapping("api/travelclassprice")
	public ResponseEntity<List<TravelclassPriceDTO>> getTravelclasssPrice(){
		List<TravelclassPriceDTO> travelclassPriceDTOs = travelclassPriceService.getList();
		if(travelclassPriceDTOs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else return new ResponseEntity<>(travelclassPriceDTOs,HttpStatus.OK);
	}
	
	@GetMapping("api/travelclassprice/{id}")
	public ResponseEntity<TravelclassPriceDTO> getTravelclassPrice(@PathVariable("id") Integer id){
		TravelclassPriceDTO travelclassPriceDTO = travelclassPriceService.findbyid(id);
		if(travelclassPriceDTO != null) {
			return new ResponseEntity<>(travelclassPriceDTO,HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("api/travelclassprice")
	public ResponseEntity<TravelclassPriceDTO> addTravelclassPrice(@RequestBody TravelclassPriceDTO travelclassPriceDTO){
		travelclassPriceService.save(travelclassPriceDTO);
		return new ResponseEntity<>(travelclassPriceDTO,HttpStatus.CREATED);
	}
	
	@PutMapping("api/travelclassprice/{id}")
	public ResponseEntity<TravelclassPriceDTO> updateTravelclassPrice(@RequestBody TravelclassPriceDTO travelclassPriceDTO, @PathVariable("id") Integer id){
		TravelclassPriceDTO _travelclassPriceDTO = travelclassPriceService.findbyid(id);
		if(_travelclassPriceDTO != null) {
			travelclassPriceService.save(travelclassPriceDTO);
			return new ResponseEntity<>(travelclassPriceDTO,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	@DeleteMapping("api/travelclassprice/{id}")
	public ResponseEntity<HttpStatus> deleteTravelclassPrice(@PathVariable int id) {
		try {
			travelclassPriceService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
