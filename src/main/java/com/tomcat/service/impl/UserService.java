package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomcat.converter.UserConverter;
import com.tomcat.dto.UserDTO;
import com.tomcat.entity.Role;
import com.tomcat.entity.User;
import com.tomcat.repository.UserRepository;
import com.tomcat.service.IUserService;

@Service
public class UserService implements IUserService{

	@Autowired
	UserConverter userConverter;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void add(UserDTO t) {
		User user = userConverter.toUser(t);
		List<Role> roles = new ArrayList<Role>();
		roles.add(new Role(2, "ROLE_USER"));
		user.setRoles(roles);
		userRepository.save(user);
	}


	@Override
	public void save(UserDTO t) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(UserDTO t) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(UserDTO t) {
		// TODO Auto-generated method stub
		
	}
	
}
