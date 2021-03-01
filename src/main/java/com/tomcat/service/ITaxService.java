package com.tomcat.service;

import java.util.List;

import com.tomcat.dto.TaxDTO;

public interface ITaxService {
	public List<TaxDTO> getTaxes();
	TaxDTO save(TaxDTO dto);
	void delete(String id);
	TaxDTO getTax(String id);
}
