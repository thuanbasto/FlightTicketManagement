package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomcat.converter.CustomerConverter;
import com.tomcat.dto.CustomerDTO;
import com.tomcat.entity.City;
import com.tomcat.entity.Customer;
import com.tomcat.repository.CustomerRepository;
import com.tomcat.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService{
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerConverter customerConverter;

	@Override
	public List<CustomerDTO> getList() {
		List<CustomerDTO> models = new ArrayList<>();
		List<Customer> entity = customerRepository.findAll();
		for(Customer item : entity) {
			CustomerDTO customerDTO = customerConverter.toDTO(item);
			models.add(customerDTO);
		}
		return models;
	}

	@Override
	public void save(CustomerDTO customerDTO) {
		Customer entity = customerConverter.toEntyti(customerDTO);
		customerRepository.save(entity);
	}

	@Override
	public CustomerDTO findbyid(int id) {
		 Customer entity = customerRepository.findOne(id);
		
		return customerConverter.toDTO(entity);
	}

	@Override
	public void delete(int id) {
		customerRepository.delete(id);
		
	}

}
