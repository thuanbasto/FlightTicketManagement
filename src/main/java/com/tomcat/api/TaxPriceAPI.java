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

import com.tomcat.dto.TaxPriceDTO;
import com.tomcat.service.ITaxPriceService;

@RestController
@RequestMapping("/api")
public class TaxPriceAPI {
	
	@Autowired
	ITaxPriceService taxpriceService;
	
	@GetMapping(value = "/taxprices")
	public ResponseEntity<List<TaxPriceDTO>> getAirports() {
		List<TaxPriceDTO> taxpricecDTOs = taxpriceService.getList();
		if(taxpricecDTOs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(taxpricecDTOs, HttpStatus.OK) ;
	}
	
	@GetMapping(value = "/taxprices/{id}")
	public ResponseEntity<TaxPriceDTO> getAirport(@PathVariable("id") int id) {
		TaxPriceDTO taxpricecDTO = taxpriceService.findbyid(id);
		if(taxpricecDTO.getTax_Price_Id() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(taxpricecDTO, HttpStatus.OK) ;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/taxprices")
	public ResponseEntity<TaxPriceDTO> addAirport(@RequestBody TaxPriceDTO taxpriceDTO) {

			taxpriceService.save(taxpriceDTO);
			return new ResponseEntity<>(taxpriceDTO, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/taxprices/{id}")
	public ResponseEntity<HttpStatus> deleteAirport(@PathVariable int id) {
		try{
			taxpriceService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return  new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/taxprices/{id}")
	public ResponseEntity<TaxPriceDTO> updateAirport(@RequestBody TaxPriceDTO taxpriceDTO,
			@PathVariable int id) {
		TaxPriceDTO _taxpriceDTO = taxpriceService.findbyid(id);
		if(_taxpriceDTO.getTax_Price_Id() != 0) {
			taxpriceService.save(taxpriceDTO);
			return new ResponseEntity<>(taxpriceDTO, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

}