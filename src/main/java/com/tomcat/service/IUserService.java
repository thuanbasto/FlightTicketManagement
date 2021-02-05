package com.tomcat.service;

import com.tomcat.dto.UserDTO;

public interface IUserService{
	public void add(UserDTO userDTO);
	public boolean checkExist(String username);
}
