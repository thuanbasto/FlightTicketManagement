package com.tomcat.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tomcat.dto.UserDTO;
import com.tomcat.service.IUserService;

@Component
public class UserValidator implements Validator{
	@Autowired
	IUserService userService;
	
	public boolean supports(Class<?> clazz) {
		return UserDTO.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		UserDTO user = (UserDTO) target;
		
		// check username
		if (userService.getUser(user.getUsername()) != null)
			errors.rejectValue("username", "user.username.exist");
		if (user.getUsername().length() == 0 || user.getUsername() == null || user.getUsername().length() < 6)
			errors.rejectValue("username", "user.username.length");
		if (!user.getUsername().matches("^[a-zA-Z0-9]+$")) 
			errors.rejectValue("username", "user.username.regex");
		
		// check password
		if (!user.getPassword().equals(user.getConfirmPassword())) 
			errors.rejectValue("password", "user.password.confirm");
	}

}
