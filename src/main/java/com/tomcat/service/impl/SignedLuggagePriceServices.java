package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomcat.converter.SignedLuggagePriceConverter;
import com.tomcat.dto.SignedluggagePriceDTO;
import com.tomcat.entity.Signedluggage;
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
		
		SignedluggagePriceDTO signedluggagePriceDTO = new SignedluggagePriceDTO();
		
		signedluggagePriceDTO = signedLuggagePriceConverter.toDTO(signedluggagePrice);
		
		return signedluggagePriceDTO;
	}
	
	@Transactional
	@Override
	public SignedluggagePriceDTO save(SignedluggagePriceDTO SignedluggagePriceDTO) {
		
		SignedluggagePrice SignedluggagePrice = new SignedluggagePrice();
		
		if(SignedluggagePriceDTO.getPrice_Id() != null && SignedluggagePriceDTO.getSignedLuggage_Id() != null ) {
			SignedluggagePrice oldSignedluggagePrice = signedLuggagePriceRepository.findOne(SignedluggagePriceDTO.getPrice_Id());				
			SignedluggagePrice = signedLuggagePriceConverter.toEntity(SignedluggagePriceDTO,oldSignedluggagePrice);
			Signedluggage Signedluggage = signedLuggageRepository.findOne(SignedluggagePriceDTO.getSignedLuggage_Id());
			SignedluggagePrice.setSignedluggage(Signedluggage);
		}else if (SignedluggagePriceDTO.getPrice_Id() != null && SignedluggagePriceDTO.getSignedLuggage_Id() == null) {
			SignedluggagePrice oldSignedluggagePrice = signedLuggagePriceRepository.findOne(SignedluggagePriceDTO.getPrice_Id());	
			SignedluggagePrice = signedLuggagePriceConverter.toEntity(SignedluggagePriceDTO,oldSignedluggagePrice);
		}else {
			SignedluggagePrice = signedLuggagePriceConverter.toEntity(SignedluggagePriceDTO);
			Signedluggage Signedluggage = signedLuggageRepository.findOne(SignedluggagePriceDTO.getSignedLuggage_Id());
			SignedluggagePrice.setSignedluggage(Signedluggage);
		}
		return signedLuggagePriceConverter.toDTO(signedLuggagePriceRepository.save(SignedluggagePrice));
	}


	@Override
	public void delete(Integer id) {
		signedLuggagePriceRepository.delete(id);	
	}

}
