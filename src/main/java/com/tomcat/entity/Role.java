package com.tomcat.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int role_Id;

	private String name;

	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="role_user"
		, joinColumns={
			@JoinColumn(name="Role_Id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="User_Id")
			}
		)
	private List<User> users;

	public Role() {
	}

	public Role(int id, String name) {
		this.role_Id = id;
		this.name = name;
	}

	public int getRole_Id() {
		return this.role_Id;
	}

	public void setRole_Id(int role_Id) {
		this.role_Id = role_Id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}