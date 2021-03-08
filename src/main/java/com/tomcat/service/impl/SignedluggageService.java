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
		List<Signedluggage> signedluggages = SignedLuggageRepository.findAll();

		for (Signedluggage signedluggage : signedluggages) {
			SignedluggageDTO signedluggageDTO = signedLuggageConverter.toDTO(signedluggage);
			signedluggageDTOs.add(signedluggageDTO);
		}

		return signedluggageDTOs;
	}
	
	@Transactional
	@Override
	public SignedluggageDTO findById(Integer id) {
		Signedluggage signedluggage = SignedLuggageRepository.findOne(id);
		SignedluggageDTO signedluggageDTO = new SignedluggageDTO();
		signedluggageDTO = signedLuggageConverter.toDTO(signedluggage);
		
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
		return signedLuggageConverter.toDTO(SignedLuggageRepository.save(signedluggage));
	}
	
	@Override
	@Transactional
	public void delete(Integer id) {
		
			SignedLuggageRepository.delete(id);
	
	}

}
