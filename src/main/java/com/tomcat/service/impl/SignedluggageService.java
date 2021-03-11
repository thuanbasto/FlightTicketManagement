package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomcat.converter.SignedLuggageConverter;
import com.tomcat.dto.SignedluggageDTO;
import com.tomcat.entity.Signedluggage;
import com.tomcat.repository.SignedLuggageRepository;
import com.tomcat.service.ISignedluggageService;

@Service
public class SignedluggageService implements ISignedluggageService {

	@Autowired
	SignedLuggageRepository signedLuggageRepository;

	@Autowired
	SignedLuggageConverter signedLuggageConverter;

	@Transactional
	@Override
	public List<SignedluggageDTO> getList() {
		List<SignedluggageDTO> signedluggageDTOs = new ArrayList<>();
		List<Object[]> objs = signedLuggageRepository.getSignedLuggages();
		
		objs.forEach(luggage -> signedluggageDTOs.add(signedLuggageConverter.toDTO(luggage)));

		return signedluggageDTOs;
	}
	
	@Transactional
	@Override
	public SignedluggageDTO findById(Integer id) {
		Object[] obj = signedLuggageRepository.getSignedLuggage(id);
		if(obj.length == 0) {
			return null;
		}
		SignedluggageDTO signedluggageDTO = signedLuggageConverter.toDTO((Object[]) obj[0]);
		return signedluggageDTO;
			 
	}
	
	@Override
	public SignedluggageDTO save(SignedluggageDTO signedluggageDTO) {
		Signedluggage signedluggage = signedLuggageConverter.toEntity(signedluggageDTO);
		signedluggage = signedLuggageRepository.save(signedluggage);		
		signedluggageDTO.setSignedLuggage_Id(signedluggage.getSignedLuggage_Id());
		return signedluggageDTO;
	}
	
	@Override
	@Transactional
	public void delete(Integer id) {
		signedLuggageRepository.delete(id);	
	}

}
