package com.tomcat.dto;

import java.io.Serializable;

public class HieuDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String hieu_Id;

	private String name;

	public HieuDTO() {
	}

	public String getHieu_Id() {
		return this.hieu_Id;
	}

	public void setHieu_Id(String hieu_Id) {
		this.hieu_Id = hieu_Id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
