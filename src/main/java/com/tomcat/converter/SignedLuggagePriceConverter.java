package com.tomcat.converter;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomcat.dto.SignedluggagePriceDTO;
import com.tomcat.entity.SignedluggagePrice;

@Component
public class SignedLuggagePriceConverter {

	@Autowired
	ModelMapper mapper;

	public SignedluggagePriceDTO toDTO(SignedluggagePrice signedluggagePrice) {

		SignedluggagePriceDTO signedluggagePriceDTO = mapper.map(signedluggagePrice, SignedluggagePriceDTO.class);

		return signedluggagePriceDTO;
	}

	public SignedluggagePrice toEntity(SignedluggagePriceDTO signedluggagePriceDTO) {

		SignedluggagePrice signedluggage = mapper.map(signedluggagePriceDTO, SignedluggagePrice.class);
		
		signedluggage.setModifiedDate(new Date());

		return signedluggage;
	}

	public SignedluggagePrice toEntity(SignedluggagePriceDTO signedluggagePriceDTO,
			SignedluggagePrice signedluggagePrice) {

		signedluggagePrice = mapper.map(signedluggagePriceDTO, SignedluggagePrice.class);

		return signedluggagePrice;
	}

}
