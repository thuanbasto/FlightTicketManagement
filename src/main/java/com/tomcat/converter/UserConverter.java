package com.tomcat.converter;

import org.springframework.stereotype.Component;

import com.tomcat.dto.UserDTO;
import com.tomcat.entity.User;

@Component
public class UserConverter {
	public User toUser(UserDTO userDTO) {
		
		User user = new User();
		return user;
	}
}
