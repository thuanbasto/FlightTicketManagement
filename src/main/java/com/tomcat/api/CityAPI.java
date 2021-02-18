package com.tomcat.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.dto.CityDTO;
import com.tomcat.service.ICityService;

@RestController
public class CityAPI {
	@Autowired
	ICityService cityService;
	
	@GetMapping(value = "/cities")
    public List<CityDTO> getCityList() {
		return cityService.getList();
    } 
}
