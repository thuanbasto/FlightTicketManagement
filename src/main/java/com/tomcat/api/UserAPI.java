package com.tomcat.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.service.IUserService;

@RestController
public class UserAPI {
	
	@Autowired
	IUserService iUserService;
	
	@GetMapping(value = "/admin/user/{username}")
    public boolean checkUserByUsername(@PathVariable(name="username") String username) {
		if (iUserService.checkExist(username)) return true;
		return false;
    } 
}
