package com.tomcat.service;

import java.util.Set;

import com.tomcat.dto.RoleDTO;

public interface IRoleService{
	Set<RoleDTO> getRoleDTOList();
	RoleDTO getRoleDTO(Integer id);
}
