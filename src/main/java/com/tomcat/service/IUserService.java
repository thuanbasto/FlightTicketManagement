package com.tomcat.service;

import com.tomcat.dto.UserDTO;

public interface IUserService extends BaseService<UserDTO>{
	public void add(UserDTO userDTO);
}
