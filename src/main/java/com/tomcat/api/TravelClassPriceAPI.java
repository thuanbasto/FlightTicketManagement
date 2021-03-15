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

import com.tomcat.dto.TravelClassPriceDTO;
import com.tomcat.service.ITravelClassPriceService;

@RestController
@RequestMapping("/api")
public class TravelClassPriceAPI {
	@Autowired
	ITravelClassPriceService travelClassPriceService;
	
	@GetMapping("/travelclassprices")
	public ResponseEntity<List<TravelClassPriceDTO>> getTravelclasssPrice(){
		List<TravelClassPriceDTO> travelclassPriceDTOs = travelClassPriceService.getTravelClassPrices();
		if(travelclassPriceDTOs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else return new ResponseEntity<>(travelclassPriceDTOs,HttpStatus.OK);
	}
	
	@GetMapping("/travelclassprices/{id}")
	public ResponseEntity<TravelClassPriceDTO> getTravelclassPrice(@PathVariable("id") String id){
		TravelClassPriceDTO travelclassPriceDTO = travelClassPriceService.getTravelClassPrice(id);
		if(travelclassPriceDTO != null) {
			return new ResponseEntity<>(travelclassPriceDTO,HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/travelclassprices")
	public ResponseEntity<TravelClassPriceDTO> addTravelclassPrice(@RequestBody TravelClassPriceDTO travelclassPriceDTO){
		travelClassPriceService.save(travelclassPriceDTO);
		return new ResponseEntity<>(travelclassPriceDTO,HttpStatus.CREATED);
		/*
		 * postman{
		 * 
		 * "modifiedDate":"1999-07-07", "price":"301", "travelClass_Id":"3" }
		 */
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/travelclassprices/{id}")
	public ResponseEntity<TravelClassPriceDTO> updateTravelclassPrice(@RequestBody TravelClassPriceDTO travelclassPriceDTO, @PathVariable("id") String id){
		TravelClassPriceDTO _travelclassPriceDTO = travelClassPriceService.getTravelClassPrice(id);
		if(_travelclassPriceDTO != null) {
			travelClassPriceService.save(travelclassPriceDTO);
			return new ResponseEntity<>(travelclassPriceDTO,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		/*
		 * postman{ "price_Id":"id o tren", "modifiedDate":"1999-07-07", "price":"301",
		 * "travelClass_Id":"3" }
		 */
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/travelclassprices/{id}")
	public ResponseEntity<HttpStatus> deleteTravelclassPrice(@PathVariable String id) {
		try {
			travelClassPriceService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
