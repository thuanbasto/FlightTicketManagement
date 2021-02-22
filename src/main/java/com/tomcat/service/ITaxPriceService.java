package com.tomcat.service;

import java.util.List;

import com.tomcat.dto.TaxPriceDTO;

public interface ITaxPriceService {
		public List<TaxPriceDTO> getList();
		TaxPriceDTO findbyid(Integer id);
		TaxPriceDTO insertTax(TaxPriceDTO dto);
		TaxPriceDTO updateTax(TaxPriceDTO dto);
		TaxPriceDTO save(TaxPriceDTO dto);
		void delete(int id);

}
