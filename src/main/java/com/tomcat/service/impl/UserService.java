package com.tomcat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomcat.converter.UserConverter;
import com.tomcat.dto.UserDTO;
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
		
		userRepository.save(user);
	}

	@Override
	public boolean checkExist(String username) {
		if (userRepository.findOneByUsername(username) == null) return false;
		return true;
	}

}
