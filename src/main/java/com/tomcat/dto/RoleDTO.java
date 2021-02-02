package com.tomcat.dto;

import java.io.Serializable;
import java.util.List;


public class RoleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer role_Id;

	private String name;

	private List<UserDTO> users;

	public RoleDTO() {
	}

	public Integer getRole_Id() {
		return this.role_Id;
	}

	public void setRole_Id(Integer role_Id) {
		this.role_Id = role_Id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserDTO> getUsers() {
		return this.users;
	}

	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}

}