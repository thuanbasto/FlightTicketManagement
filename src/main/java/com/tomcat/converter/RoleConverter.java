package com.tomcat.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomcat.dto.RoleDTO;
import com.tomcat.entity.Role;

@Component
public class RoleConverter {
	
	@Autowired
	ModelMapper mapper;
	
	public Role toRole(RoleDTO roleDTO) {
		Role role = mapper.map(roleDTO, Role.class);
		return role;
	}
	
	public RoleDTO toRoleDTO(Role role) {
		RoleDTO roleDTO = mapper.map(role, RoleDTO.class);
		return roleDTO;
	}
	
}
