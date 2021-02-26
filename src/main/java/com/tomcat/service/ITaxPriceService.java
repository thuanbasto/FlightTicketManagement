package com.tomcat.service;

import java.util.List;

import com.tomcat.dto.TaxPriceDTO;

public interface ITaxPriceService {
		public List<TaxPriceDTO> getList();
		TaxPriceDTO findbyid(Integer id);
		void save(TaxPriceDTO dto);
		void delete(int id);

}