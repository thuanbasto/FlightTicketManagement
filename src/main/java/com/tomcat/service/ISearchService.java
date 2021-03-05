package com.tomcat.service;

import java.util.List;

import com.tomcat.dto.SearchDTO;

public interface ISearchService {
	public List<SearchDTO> getSearchs(String di,String den,String ngay,String hang);
}
