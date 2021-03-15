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

import com.tomcat.dto.TaxDTO;
import com.tomcat.service.ITaxService;

@RestController
@RequestMapping("/api")
public class TaxAPI {

	@Autowired
	ITaxService taxService;

	@GetMapping(value = "/taxes")
	public ResponseEntity<List<TaxDTO>> getTaxs() {
		List<TaxDTO> taxDTOs = taxService.getTaxes();
		if (taxDTOs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(taxDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/taxes/{id}")
	public ResponseEntity<TaxDTO> getTax(@PathVariable("id") String id) {
		TaxDTO taxDTO = taxService.getTax(id);
		if (taxDTO.getTax_Id() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(taxDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/taxes")
	public ResponseEntity<TaxDTO> addTax(@RequestBody TaxDTO taxDTO) {
		taxDTO = taxService.save(taxDTO);
		return new ResponseEntity<>(taxDTO, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/taxes/{id}")
	public ResponseEntity<HttpStatus> deleteTax(@PathVariable("id") String id) {
		try {
			taxService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/taxes/{id}")
	public ResponseEntity<TaxDTO> updateTax(@RequestBody TaxDTO taxDTO,@PathVariable("id") String id) {
		TaxDTO _taxDTO = taxService.getTax(id);
		if (_taxDTO != null) {
			taxService.save(taxDTO);
			return new ResponseEntity<>(taxDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
