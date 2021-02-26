package com.tomcat.service;

import java.util.List;

import com.tomcat.dto.SignedluggagePriceDTO;

public interface ISignedLuggagePriceServices {
	
	public List<SignedluggagePriceDTO> getList();

	SignedluggagePriceDTO findById(Integer id);

	SignedluggagePriceDTO save(SignedluggagePriceDTO SignedluggagePriceDTO);
	
	public void delete(Integer id) ;

}
