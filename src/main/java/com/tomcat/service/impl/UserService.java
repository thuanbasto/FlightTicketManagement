package com.tomcat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomcat.converter.UserConverter;
import com.tomcat.dto.UserDTO;
import com.tomcat.entity.Role;
import com.tomcat.entity.User;
import com.tomcat.repository.RoleRepository;
import com.tomcat.repository.UserRepository;
import com.tomcat.service.IUserService;

@Service
public class UserService implements IUserService{

	@Autowired
	UserConverter userConverter;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public void add(UserDTO userDTO) {
		User user = userConverter.toUser(userDTO);
		
		List<Role> roles = roleRepository.findAll();
		
		user.setRoles(roles);
		user.setEnable((byte) 1);
		
		userRepository.save(user);
	}

}
