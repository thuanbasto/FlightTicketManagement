package com.tomcat.converter;

import org.springframework.stereotype.Component;

import com.tomcat.dto.HieuDTO;
import com.tomcat.entity.Hieu;

@Component
public class HieuConverter {
	public HieuDTO toDTO(Hieu hieu) {
		HieuDTO hieuDTO = new HieuDTO();
		hieuDTO.setHieu_Id(hieu.getHieu_Id());
		hieuDTO.setName(hieu.getName());
		return hieuDTO;
	}
	
	public Hieu toEntity(HieuDTO hieuDTO) {
		Hieu hieu = new Hieu();
		hieu.setHieu_Id(hieuDTO.getHieu_Id());
		hieu.setName(hieuDTO.getName());
		return hieu;
	}
}
