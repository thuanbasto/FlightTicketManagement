package com.tomcat.converter;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.tomcat.dto.UserDTO;
import com.tomcat.entity.Role;
import com.tomcat.entity.User;
import com.tomcat.enums.RoleEnum;
import com.tomcat.repository.RoleRepository;

@Component
public class UserConverter {
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	RoleRepository roleRepository;
	
	public User toUser(UserDTO userDTO) {
		// lock account
		if (userDTO.getEnable() == null) userDTO.setEnable((byte) 0);
		// encoder password
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		// lowercase for username
		userDTO.setUsername(userDTO.getUsername().toLowerCase());
		// set roles 
		Set<Role> roles = new HashSet<Role>();
		if (userDTO.getRoleIdList() != null && userDTO.getRoleIdList().size() > 0) {
			userDTO.getRoleIdList().forEach(
					role_Id -> roles.add(roleRepository.findOne(Integer.valueOf(role_Id))));
		} else {
			// if null set role default for user is ROLE_STAFF
			roles.add(roleRepository.findOne(RoleEnum.ROLE_STAFF.getId()));
		}
		
		User user = mapper.map(userDTO, User.class);
		user.setRoles(roles);
		
		return user;
	}
}
