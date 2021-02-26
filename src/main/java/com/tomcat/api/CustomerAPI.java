package com.tomcat.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.dto.CustomerDTO;
import com.tomcat.service.ICustomerService;

@RestController
@RequestMapping("/api")
public class CustomerAPI {
	
	@Autowired
	ICustomerService customerService;
	
	@GetMapping("/customers")
	public ResponseEntity<List<CustomerDTO>> getCustomers() {
		List<CustomerDTO> customerDTOs = customerService.getList();
		if(customerDTOs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(customerDTOs, HttpStatus.OK) ;
	}
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("id") Integer id) {
		CustomerDTO customerDTO= customerService.findbyid(id);
		if (customerDTO == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(customerDTO, HttpStatus.OK);
		}
		
	}
	
	@PostMapping("/customers")
	public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO customerDTO){
		customerService.save(customerDTO);
		return new ResponseEntity<>(customerDTO,HttpStatus.CREATED);
	}
	
	@PutMapping("/customers/{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable("id") Integer id){
		CustomerDTO _customerDTO = customerService.findbyid(id);
		if(_customerDTO != null) {
			customerService.save(customerDTO);
			return new ResponseEntity<>(customerDTO,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<HttpStatus> deleteTax(@PathVariable int id) {
		try {
			customerService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
