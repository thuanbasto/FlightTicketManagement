package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomcat.converter.SignedLuggagePriceConverter;
import com.tomcat.dto.SignedluggagePriceDTO;
import com.tomcat.entity.SignedluggagePrice;
import com.tomcat.repository.SignedLuggagePriceRepository;
import com.tomcat.repository.SignedLuggageRepository;
import com.tomcat.service.ISignedLuggagePriceServices;

@Service
public class SignedLuggagePriceServices implements ISignedLuggagePriceServices {

	@Autowired
	SignedLuggagePriceRepository signedLuggagePriceRepository;
	

	@Autowired
	SignedLuggageRepository signedLuggageRepository;
	
	

	@Autowired
	SignedLuggagePriceConverter signedLuggagePriceConverter;

	@Transactional
	@Override
	public List<SignedluggagePriceDTO> getList() {
		List<SignedluggagePriceDTO> signedluggagePriceDTOs = new ArrayList<SignedluggagePriceDTO>();
		List<SignedluggagePrice> signedluggagePrices = signedLuggagePriceRepository.findAll();

		for (SignedluggagePrice signedluggagePrice : signedluggagePrices) {
			SignedluggagePriceDTO signedluggagePriceDTO = signedLuggagePriceConverter.toDTO(signedluggagePrice);
			signedluggagePriceDTOs.add(signedluggagePriceDTO);
		}

		return signedluggagePriceDTOs;
	}
	
	
	@Transactional
	@Override
	public SignedluggagePriceDTO findById(Integer id) {
		SignedluggagePrice signedluggagePrice = signedLuggagePriceRepository.findOne(id);
		
		if(signedluggagePrice != null) {
			SignedluggagePriceDTO signedluggagePriceDTO = signedLuggagePriceConverter.toDTO(signedluggagePrice);
			return signedluggagePriceDTO;
		}
		
		return new SignedluggagePriceDTO();
	}
	
	@Transactional
	@Override
	public SignedluggagePriceDTO save(SignedluggagePriceDTO signedluggagePriceDTO) {
		SignedluggagePrice signedluggagePrice = signedLuggagePriceConverter.toEntity(signedluggagePriceDTO);
		signedluggagePrice = signedLuggagePriceRepository.save(signedluggagePrice);
		signedluggagePriceDTO.setPrice_Id(signedluggagePrice.getPrice_Id());
		return signedluggagePriceDTO;
	}


	@Override
	public void delete(Integer id) {
		signedLuggagePriceRepository.delete(id);	
	}

}
