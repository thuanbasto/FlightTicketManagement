package com.tomcat.service;

import java.util.List;

import com.tomcat.dto.SignedluggageDTO;

public interface ISignedluggageService {
	public List<SignedluggageDTO> getList();
	SignedluggageDTO findById(Integer id);
	SignedluggageDTO save(SignedluggageDTO dto);
	public void delete(Integer id) ;	
}