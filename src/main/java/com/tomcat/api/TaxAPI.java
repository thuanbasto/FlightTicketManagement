package com.tomcat.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.dto.TaxDTO;
import com.tomcat.service.ITaxService;

@RestController
@RequestMapping("/api")
public class TaxAPI {

	@Autowired
	ITaxService taxService;

	/*
	 * @PostMapping("api/tax") public TaxDTO createTaxPrice (@RequestBody TaxDTO
	 * taxpriceDTO) { return taxService.save(taxpriceDTO);
	 * 
	 * }
	 * 
	 * @PutMapping("api/tax") public TaxDTO updateTaxPrice (@RequestBody TaxDTO
	 * taxDTO) { return taxService.save(taxDTO); taxpriceService.save(taxpriceDTO);
	 * 
	 * }
	 * 
	 * @DeleteMapping("api/tax") public void deleteTaxPrice (@RequestBody int[] ids)
	 * { taxService.delete(ids);
	 * 
	 * }
	 */

	@RequestMapping(value = "/tax", method = RequestMethod.GET)
	public ResponseEntity<List<TaxDTO>> getTaxs() {
		List<TaxDTO> taxDTOs = taxService.getList();
		if (taxDTOs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(taxDTOs, HttpStatus.OK);
	}

	@RequestMapping(value = "/tax/{id}", method = RequestMethod.GET)
	public ResponseEntity<TaxDTO> getTax(@PathVariable("id") Integer id) {
		TaxDTO taxDTO = taxService.findbyid(id);
		if (taxDTO.getTax_Id() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(taxDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/tax", method = RequestMethod.POST)
	public ResponseEntity<TaxDTO> addTax(@RequestBody TaxDTO taxDTO) {
		TaxDTO _taxDTO = taxService.findbyid(taxDTO.getTax_Id());
		if (_taxDTO.getTax_Id() == 0) {
			taxService.save(taxDTO);
			return new ResponseEntity<>(taxDTO, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/tax/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteTax(@PathVariable int[] id) {
		try {
			taxService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@RequestMapping(value = "/tax/{id}", method = RequestMethod.PUT)
	public ResponseEntity<TaxDTO> updateTax(@RequestBody TaxDTO taxDTO) {
		TaxDTO _taxDTO = taxService.findbyid(taxDTO.getTax_Id());
		if (_taxDTO.getTax_Id() != 0) {
			taxService.save(taxDTO);
			return new ResponseEntity<>(taxDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
