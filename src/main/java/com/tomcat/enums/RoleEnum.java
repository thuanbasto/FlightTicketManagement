package com.tomcat.enums;

public enum RoleEnum {
	ROLE_ADMIN(1),
	ROLE_STAFF(2);
	private int id;

	private RoleEnum(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
