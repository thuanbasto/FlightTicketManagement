package com.tomcat.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.dto.CityDTO;
import com.tomcat.entity.City;
import com.tomcat.service.ICityService;

@RestController
@RequestMapping("/api")
public class CityAPI {
	
	@Autowired
	private ICityService cityService;
	
	@RequestMapping(value = "/citys", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<CityDTO> getCitys(){
		return cityService.getList();
	}
	
	@RequestMapping(value = "/citys", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void addCity(@RequestBody CityDTO cityDTO){
		cityService.save(cityDTO);
	}
	
	@RequestMapping(value = "/citys/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteCity(@PathVariable String id){
		cityService.delete(id);
	}
	
	@RequestMapping(value = "/citys", method = RequestMethod.PUT)
	public void updateCity(@RequestBody CityDTO cityDTO) {
		cityService.save(cityDTO);
	}
}
