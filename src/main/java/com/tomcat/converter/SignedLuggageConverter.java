package com.tomcat.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomcat.dto.SignedluggageDTO;
import com.tomcat.dto.SignedluggagePriceDTO;
import com.tomcat.entity.Signedluggage;
import com.tomcat.entity.SignedluggagePrice;

@Component
public class SignedLuggageConverter {

	@Autowired
	ModelMapper mapper;

	public SignedluggageDTO toDTO(Signedluggage signedluggage) {
		
		if(signedluggage.getSignedluggagePrices() == null) {
			
			List<SignedluggagePrice> list = new ArrayList<SignedluggagePrice>();
			
			signedluggage.setSignedluggagePrices(list);
		}

		SignedluggageDTO signedluggageDTO = mapper.map(signedluggage, SignedluggageDTO.class);

		List<SignedluggagePriceDTO> signedluggagePrices = new ArrayList<SignedluggagePriceDTO>();
		
		if(signedluggageDTO.getSignedluggagePrices().isEmpty()) {
			signedluggageDTO.setSignedluggagePrices(null);
		}else {
			signedluggagePrices.add(signedluggageDTO.getSignedluggagePrices().get(signedluggageDTO.getSignedluggagePrices().size()-1));
			signedluggageDTO.setSignedluggagePrices(signedluggagePrices);
		}
		

		signedluggageDTO.setTickets(null);

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

