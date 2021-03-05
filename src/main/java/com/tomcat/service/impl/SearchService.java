package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomcat.converter.SearchConverter;
import com.tomcat.dto.FlightDTO;
import com.tomcat.dto.SearchDTO;
import com.tomcat.repository.FlightRepository;
import com.tomcat.service.ISearchService;

@Service
public class SearchService implements ISearchService{
	
	@Autowired
	SearchConverter searchConverter;
	
	@Autowired
	FlightRepository flightRepository;

	@Override
	public List<SearchDTO> getSearchs(String di,String den,String ngay,String hang) {
		//"DAD","HCM","2021-01-03"
		List<Object[]> searchs = flightRepository.findSeach1(di,den,ngay,hang);
		List<SearchDTO> searchDTOs = new ArrayList<>();
		searchs.forEach(search->searchDTOs.add(searchConverter.toSearchDTO(search)));
		return searchDTOs;
	}
	

}
