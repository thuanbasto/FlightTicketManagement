package com.tomcat.converter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	public Set<RoleDTO> toRoleDTO(List<Role> listRole) {
		Set<RoleDTO> listRoleDTO = new HashSet<RoleDTO>();
		listRole.forEach(role -> {
			RoleDTO roleDTO = new RoleDTO();
			roleDTO.setRole_Id(role.getRole_Id());
			roleDTO.setName(role.getName());
			listRoleDTO.add(roleDTO);
		});
		return listRoleDTO;
	}
	
}
