package com.tomcat.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class UserValidator {
//	@Autowired
//	UserDao userDao;
	
//	public boolean supports(Class<?> clazz) {
//		return UserDTO.class.isAssignableFrom(clazz);
//	}

	public void validate(Object target, Errors errors) {
//		UserDTO user = (UserDTO) target;
		
		// check username
//		if (!userDao.checkUsername(user.getUsername()))
//			errors.rejectValue("username", "user.username.exist");
//		if (user.getUsername().length() == 0 || user.getUsername() == null || user.getUsername().length() < 6)
//			errors.rejectValue("username", "user.username.length");
//		if (!user.getUsername().matches("^[a-zA-Z0-9\\-]+$")) 
//			errors.rejectValue("username", "user.username.regex");
//		
//		// check password
//		if (!user.getPassword().equals(user.getConfirmPassword())) 
//			errors.rejectValue("password", "user.password.confirm");
	}

}
