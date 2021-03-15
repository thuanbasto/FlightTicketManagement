package com.tomcat.api;

import java.util.List;

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

import com.tomcat.dto.SignedluggagePriceDTO;
import com.tomcat.service.ISignedLuggagePriceServices;

@RestController
@RequestMapping("/api")
public class SignedLuggagePriceAPI {

	@Autowired
	ISignedLuggagePriceServices signedLuggagePriceServices;

	@GetMapping(value = "/signedluggageprice", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<SignedluggagePriceDTO>> getLuggagePrices() {
		List<SignedluggagePriceDTO> signedluggagePriceDTOs = signedLuggagePriceServices.getList();
		if (signedluggagePriceDTOs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(signedluggagePriceDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/signedluggageprice/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SignedluggagePriceDTO> getLuggagePrice(@PathVariable("id") Integer id) {
		SignedluggagePriceDTO signedluggagePriceDTO = signedLuggagePriceServices.findById(id);
		if (signedluggagePriceDTO.getPrice_Id() == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(signedluggagePriceDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/signedluggageprice", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SignedluggagePriceDTO> createLuggagePrice(
			@RequestBody SignedluggagePriceDTO signedluggagePriceDTO) {
		signedluggagePriceDTO = signedLuggagePriceServices.save(signedluggagePriceDTO);
		return new ResponseEntity<>(signedluggagePriceDTO, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/signedluggageprice/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SignedluggagePriceDTO> updateLuggagePrice(@RequestBody SignedluggagePriceDTO signedluggagePriceDTO, @PathVariable("id") Integer id) {
		
		SignedluggagePriceDTO _signedluggagePriceDTO = signedLuggagePriceServices.findById(id);

		if (_signedluggagePriceDTO != null) {
			signedLuggagePriceServices.save(signedluggagePriceDTO);
			return new ResponseEntity<>(signedluggagePriceDTO, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/signedluggageprice/{id}")
	public ResponseEntity<String> deleteLuggage(@PathVariable("id") Integer id) {

		try {
			signedLuggagePriceServices.delete(id);
			return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
