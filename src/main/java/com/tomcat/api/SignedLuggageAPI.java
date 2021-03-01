package com.tomcat.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.dto.SignedluggageDTO;
import com.tomcat.service.ISignedluggageService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class SignedLuggageAPI {

	@Autowired
	ISignedluggageService signedLuggageService;

	@RequestMapping(value = "/signedluggage", method = RequestMethod.GET ,produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<SignedluggageDTO>> getLuggages() {
		List<SignedluggageDTO> signedluggageDTOs = signedLuggageService.getList();
		if (signedluggageDTOs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(signedluggageDTOs, HttpStatus.OK);
	}

	@RequestMapping(value = "/signedluggage/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SignedluggageDTO> getLuggage(@PathVariable("id") Integer id) {
		SignedluggageDTO signedluggageDTO = signedLuggageService.findById(id);
		if (signedluggageDTO.getSignedLuggage_Id() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(signedluggageDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/signedluggage", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SignedluggageDTO> createLuggage(@RequestBody SignedluggageDTO signedluggageDTO) {
		SignedluggageDTO _signedluggageDTO = signedLuggageService.save(signedluggageDTO);

		if (_signedluggageDTO == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(_signedluggageDTO, HttpStatus.OK);

	}

	@RequestMapping(value = "/signedluggage", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SignedluggageDTO> updateLuggage(@RequestBody SignedluggageDTO signedluggageDTO) {
		SignedluggageDTO _signedluggageDTO = signedLuggageService.save(signedluggageDTO);

		if (_signedluggageDTO == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(_signedluggageDTO, HttpStatus.OK);

	}

	@RequestMapping(value = "/signedluggage/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> updateLuggage(@PathVariable("id") Integer id) {
		
		try {
			signedLuggageService.delete(id);
			return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

}

