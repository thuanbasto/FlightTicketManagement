package com.tomcat.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.dto.SignedluggagePriceDTO;
import com.tomcat.service.ISignedLuggagePriceServices;

@RestController
@RequestMapping("/api")
public class SignedLuggagePriceAPI {

	@Autowired
	ISignedLuggagePriceServices signedLuggagePriceServices;

	@RequestMapping(value = "/signedluggageprice", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<SignedluggagePriceDTO>> getLuggagePrices() {
		List<SignedluggagePriceDTO> signedluggagePriceDTOs = signedLuggagePriceServices.getList();
		if (signedluggagePriceDTOs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(signedluggagePriceDTOs, HttpStatus.OK);
	}

	@RequestMapping(value = "/signedluggageprice/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SignedluggagePriceDTO> getLuggagePrice(@PathVariable("id") Integer id) {
		SignedluggagePriceDTO signedluggagePriceDTO = signedLuggagePriceServices.findById(id);
		if (signedluggagePriceDTO == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(signedluggagePriceDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/signedluggageprice", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SignedluggagePriceDTO> createLuggagePrice(
			@RequestBody SignedluggagePriceDTO signedluggagePriceDTO) {
		SignedluggagePriceDTO _signedluggagePriceDTO = signedLuggagePriceServices.save(signedluggagePriceDTO);

		if (_signedluggagePriceDTO == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(_signedluggagePriceDTO, HttpStatus.OK);

	}

	@RequestMapping(value = "/signedluggageprice", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SignedluggagePriceDTO> updateLuggagePrice(
			@RequestBody SignedluggagePriceDTO signedluggagePriceDTO) {
		SignedluggagePriceDTO _signedluggagePriceDTO = signedLuggagePriceServices.save(signedluggagePriceDTO);

		if (_signedluggagePriceDTO == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(_signedluggagePriceDTO, HttpStatus.OK);

	}

	@RequestMapping(value = "/signedluggageprice/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteLuggage(@PathVariable("id") Integer id) {

		try {
			signedLuggagePriceServices.delete(id);
			return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
