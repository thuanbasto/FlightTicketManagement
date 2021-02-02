package com.tomcat.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomcat.dto.UserDTO;
import com.tomcat.entity.User;

@Component
public class UserConverter {
	@Autowired
	ModelMapper mapper;
	
	public User toUser(UserDTO userDTO) {
		User user = mapper.map(userDTO, User.class);
		return user;
	}
}
