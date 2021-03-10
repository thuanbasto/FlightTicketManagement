package com.tomcat.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomcat.dto.SignedluggageDTO;
import com.tomcat.dto.SignedluggagePriceDTO;
import com.tomcat.entity.Signedluggage;

@Component
public class SignedLuggageConverter {

	@Autowired
	ModelMapper mapper;

	public SignedluggageDTO toDTO(Object[] obj) {
		
		SignedluggageDTO signedluggageDTO = new SignedluggageDTO();
		signedluggageDTO.setSignedLuggage_Id((Integer)obj[0]);
		signedluggageDTO.setName((String)obj[1]);
		signedluggageDTO.setWeight((Double)obj[2]);
		
		SignedluggagePriceDTO signedluggagePriceDTO = new SignedluggagePriceDTO();
		signedluggagePriceDTO.setPrice_Id((Integer)obj[3]);
		signedluggagePriceDTO.setPrice((Double)obj[4]);
		signedluggagePriceDTO.setModifiedDate((Date)obj[5]);
		
		List<SignedluggagePriceDTO> signedluggagePriceDTOs = new ArrayList<SignedluggagePriceDTO>();
		signedluggagePriceDTOs.add(signedluggagePriceDTO);
		
		signedluggageDTO.setSignedluggagePrices(signedluggagePriceDTOs);
		
		return signedluggageDTO;
	}

	public Signedluggage toEntity(SignedluggageDTO signedluggageDTO) {

		Signedluggage signedluggage = mapper.map(signedluggageDTO, Signedluggage.class);

		return signedluggage;
	}

	public Signedluggage toEntity(SignedluggageDTO signedluggageDTO, Signedluggage signedluggage) {

		signedluggage = mapper.map(signedluggageDTO, Signedluggage.class);

		return signedluggage;
	}

}

