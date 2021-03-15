package com.tomcat.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.tomcat.dto.CityDTO;
import com.tomcat.service.ICityService;

@RestController
@RequestMapping("/api")
public class CityAPI {
	
	@Autowired
	private ICityService cityService;
	
	@GetMapping(value = "/cities")
	public ResponseEntity<List<CityDTO>> getCities(){
		List<CityDTO> cityDTOs = cityService.getList();
		if(cityDTOs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(cityDTOs, HttpStatus.OK) ;
	}
	
	@GetMapping(value = "/cities/{id}")
	public ResponseEntity<CityDTO> getCity(@PathVariable("id") String id){
		CityDTO cityDTO = cityService.get(id);
		if(cityDTO.getCity_Id() == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(cityDTO, HttpStatus.OK) ;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/cities", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CityDTO> addCity(@Valid @RequestBody CityDTO cityDTO){
			cityService.save(cityDTO);
			return new ResponseEntity<CityDTO>(cityDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/cities/{id}")
	public ResponseEntity<HttpStatus> deleteCity(@PathVariable String id){
		try{
			cityService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return  new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/cities/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CityDTO> updateCity(@Valid @RequestBody CityDTO cityDTO) {
		CityDTO _cityDTO = cityService.get(cityDTO.getCity_Id());
		if(_cityDTO.getCity_Id() != null) {
			cityService.save(cityDTO);
			return new ResponseEntity<>(cityDTO, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
