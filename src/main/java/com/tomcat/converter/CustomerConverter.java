package com.tomcat.converter;

import org.springframework.stereotype.Component;

import com.tomcat.dto.CustomerDTO;
import com.tomcat.entity.Customer;

@Component
public class CustomerConverter {

	public CustomerDTO toDTO(Customer entity) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomer_Id(entity.getCustomer_Id());
		customerDTO.setAddress(entity.getAddress());
		customerDTO.setBirthDay(entity.getBirthDay());
		customerDTO.setFirstName(entity.getFirstName());
		customerDTO.setIdentityNumber(entity.getIdentityNumber());
		customerDTO.setLastName(entity.getLastName());
		customerDTO.setTickets(null);
		return customerDTO;
	}
	
	public Customer toEntyti(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setCustomer_Id(customerDTO.getCustomer_Id());
		customer.setAddress(customerDTO.getAddress());
		customer.setBirthDay(customerDTO.getBirthDay());
		customer.setFirstName(customerDTO.getFirstName());
		customer.setIdentityNumber(customerDTO.getIdentityNumber());
		customer.setLastName(customerDTO.getLastName());
		customer.setTickets(null);
		return customer;
	}
	
	public Customer toEntyti(CustomerDTO customerDTO,Customer customer) {
		customer.setCustomer_Id(customerDTO.getCustomer_Id());
		customer.setAddress(customerDTO.getAddress());
		customer.setBirthDay(customerDTO.getBirthDay());
		customer.setFirstName(customerDTO.getFirstName());
		customer.setIdentityNumber(customerDTO.getIdentityNumber());
		customer.setLastName(customerDTO.getLastName());
		customer.setTickets(null);
		return customer;
	}
}
