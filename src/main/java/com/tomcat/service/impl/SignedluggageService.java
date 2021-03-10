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
	SignedLuggageRepository SignedLuggageRepository;

	@Autowired
	SignedLuggageConverter signedLuggageConverter;

	@Transactional
	@Override
	public List<SignedluggageDTO> getList() {
		List<SignedluggageDTO> signedluggageDTOs = new ArrayList<>();
		List<Object[]> objs = SignedLuggageRepository.getSignedLuggages();
		
		objs.forEach(luggage -> signedluggageDTOs.add(signedLuggageConverter.toDTO(luggage)));

		return signedluggageDTOs;
	}
	
	@Transactional
	@Override
	public SignedluggageDTO findById(Integer id) {
		Object[] obj = SignedLuggageRepository.getSignedLuggage(id);
		if(obj.length == 0) {
			return null;
		}
		SignedluggageDTO signedluggageDTO = signedLuggageConverter.toDTO((Object[]) obj[0]);
		return signedluggageDTO;
			 
	}
	
	@Override
	public SignedluggageDTO save(SignedluggageDTO signedluggageDTO) {
		Signedluggage signedluggage = new Signedluggage();
		if(signedluggageDTO.getSignedLuggage_Id() != null) {
			Signedluggage oldSignedluggage = SignedLuggageRepository.findOne(signedluggageDTO.getSignedLuggage_Id());
			signedluggage = signedLuggageConverter.toEntity(signedluggageDTO, oldSignedluggage);
		}else {
			signedluggage = signedLuggageConverter.toEntity(signedluggageDTO);
		}
		return null;
	}
	
	@Override
	@Transactional
	public void delete(Integer id) {
		
			SignedLuggageRepository.delete(id);
	
	}

}
