package com.tomcat.entity;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@NamedQuery(name="Hieu.findAll", query="SELECT a FROM Hieu a")
public class Hieu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String hieu_Id;

	@Column
	private String name;

	public Hieu() {
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
