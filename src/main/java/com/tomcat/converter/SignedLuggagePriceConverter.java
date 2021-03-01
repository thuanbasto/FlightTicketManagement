package com.tomcat.converter;

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

		// signedluggagePriceDTO.setSignedLuggage_Id(null);

		/*
		 * if(signedluggagePrice.getSignedluggage() != null) {
		 * signedluggagePriceDTO.setSignedLuggage_Id(signedluggagePrice.getSignedluggage
		 * ().getSignedLuggage_Id()); }else {
		 * signedluggagePriceDTO.setSignedLuggage_Id(null); }
		 */

		return signedluggagePriceDTO;
	}

	public SignedluggagePrice toEntity(SignedluggagePriceDTO signedluggagePriceDTO) {

		SignedluggagePrice signedluggage = mapper.map(signedluggagePriceDTO, SignedluggagePrice.class);

		return signedluggage;
	}

	public SignedluggagePrice toEntity(SignedluggagePriceDTO signedluggagePriceDTO,
			SignedluggagePrice signedluggagePrice) {

		signedluggagePrice = mapper.map(signedluggagePriceDTO, SignedluggagePrice.class);

		// signedluggagePriceDTO.setSignedLuggage_Id(signedluggagePrice.getSignedluggage().getSignedLuggage_Id());

		return signedluggagePrice;
	}

}
