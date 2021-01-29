package com.tomcat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomcat.converter.RoleConverter;
import com.tomcat.dto.RoleDTO;
import com.tomcat.entity.Role;
import com.tomcat.repository.RoleRepository;
import com.tomcat.service.IRoleService;

@Service
public class RoleService implements IRoleService{
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	RoleConverter roleConverter;
	
	@Override
	public RoleDTO getRoleDTO(Long id) {
		Role role = roleRepository.findOne(id);
		RoleDTO roleDTO = roleConverter.toRoleDTO(role);
		return roleDTO;
	}
}
