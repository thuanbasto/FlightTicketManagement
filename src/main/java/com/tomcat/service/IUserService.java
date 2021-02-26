package com.tomcat.service;

import java.util.List;

import com.tomcat.dto.UserDTO;

public interface IUserService{
	public void save(UserDTO userDTO);
	public boolean generateAccountAdmin();
	public List<UserDTO> getUsers();
	public UserDTO getUser(String id);
	public void delete(String id);
}
