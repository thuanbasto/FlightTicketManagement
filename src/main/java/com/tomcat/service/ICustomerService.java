package com.tomcat.service;

import java.util.List;

import com.tomcat.dto.CustomerDTO;

public interface ICustomerService {
	public List<CustomerDTO> getList();
	CustomerDTO save(CustomerDTO customerDTO);
	CustomerDTO findbyid(int id);
	void delete(int id);

}
