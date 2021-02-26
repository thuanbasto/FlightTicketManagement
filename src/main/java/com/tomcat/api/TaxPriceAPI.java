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

import com.tomcat.dto.TaxPriceDTO;
import com.tomcat.service.ITaxPriceService;

@RestController
@RequestMapping("/api")
public class TaxPriceAPI {
	
	@Autowired
	ITaxPriceService taxpriceService;
	
	/*
	 * @GetMapping("/taxprice") public List<TaxPriceDTO> getTaxPrice() { return
	 * taxpriceService.getList(); }
	 * 
	 * 
	 * @PostMapping("/taxprice") public TaxPriceDTO createTaxPrice (@RequestBody
	 * TaxPriceDTO taxpriceDTO) { return taxpriceService.save(taxpriceDTO);
	 * 
	 * }
	 * 
	 * @PutMapping("/taxprice") public TaxPriceDTO updateTaxPrice (@RequestBody
	 * TaxPriceDTO taxpriceDTO) { return taxpriceService.save(taxpriceDTO);
	 * 
	 * }
	 * 
	 * @DeleteMapping("/taxprice") public void deleteTaxPrice (@RequestBody int[]
	 * ids) { taxpriceService.delete(ids);
	 * 
	 * }
	 */
	
	
	@RequestMapping(value = "/taxprice", method = RequestMethod.GET)
	public ResponseEntity<List<TaxPriceDTO>> getAirports() {
		List<TaxPriceDTO> taxpricecDTOs = taxpriceService.getList();
		if(taxpricecDTOs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(taxpricecDTOs, HttpStatus.OK) ;
	}
	
	@RequestMapping(value = "/taxprice/{id}", method = RequestMethod.GET)
	public ResponseEntity<TaxPriceDTO> getAirport(@PathVariable("id") int id) {
		TaxPriceDTO taxpricecDTO = taxpriceService.findbyid(id);
		if(taxpricecDTO.getTax_Price_Id() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(taxpricecDTO, HttpStatus.OK) ;
	}
	
	@RequestMapping(value = "/taxprice", method = RequestMethod.POST)
	public ResponseEntity<TaxPriceDTO> addAirport(@RequestBody TaxPriceDTO taxpriceDTO) {

			taxpriceService.save(taxpriceDTO);
			return new ResponseEntity<>(taxpriceDTO, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/taxprice/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<HttpStatus> deleteAirport(@PathVariable int id) {
		try{
			taxpriceService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return  new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@RequestMapping(value = "/taxprice/{id}", method = RequestMethod.PUT)
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