package com.tomcat.service;

import java.util.List;

import com.tomcat.dto.CityDTO;
import com.tomcat.entity.City;

public interface ICityService extends BaseService<CityDTO>{
	public List<City> getList();
	public City getCity(String id);
}
