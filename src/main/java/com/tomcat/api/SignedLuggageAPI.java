package com.tomcat.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.dto.SignedluggageDTO;
import com.tomcat.service.ISignedluggageService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class SignedLuggageAPI {

	@Autowired
	ISignedluggageService signedLuggageService;

	@GetMapping(value = "/signedluggage", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<SignedluggageDTO>> getLuggages() {
		List<SignedluggageDTO> signedluggageDTOs = signedLuggageService.getList();
		if (signedluggageDTOs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(signedluggageDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/signedluggage/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SignedluggageDTO> getLuggage(@PathVariable("id") Integer id) {
		SignedluggageDTO signedluggageDTO = signedLuggageService.findById(id);
		if (signedluggageDTO.getSignedLuggage_Id() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(signedluggageDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/signedluggage", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SignedluggageDTO> addLuggage(@RequestBody SignedluggageDTO signedluggageDTO) {
		signedluggageDTO = signedLuggageService.save(signedluggageDTO);
		return new ResponseEntity<>(signedluggageDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/signedluggage/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SignedluggageDTO> updateLuggage(@RequestBody SignedluggageDTO signedluggageDTO,@PathVariable("id") Integer id) {
		
		SignedluggageDTO _signedluggageDTO = signedLuggageService.findById(id);

		if (_signedluggageDTO != null) {
			signedLuggageService.save(signedluggageDTO);
			return new ResponseEntity<>(signedluggageDTO,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/signedluggage/{id}")
	public ResponseEntity<String> deleteLuggage(@PathVariable("id") Integer id) {
		try {
			signedLuggageService.delete(id);
			return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("deleted found",HttpStatus.NOT_FOUND);
		}

	}

}
