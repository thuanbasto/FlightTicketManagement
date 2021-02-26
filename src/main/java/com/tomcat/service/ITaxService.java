package com.tomcat.service;

import java.util.List;
import java.util.Map;

import com.tomcat.dto.TaxDTO;

public interface ITaxService {
	public List<TaxDTO> getList();
	Map<Integer, String> findAll();
	TaxDTO save(TaxDTO dto);
	void delete(int[] id);
	TaxDTO findbyid(Integer id);
}
