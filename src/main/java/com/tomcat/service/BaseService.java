package com.tomcat.service;

public interface BaseService<T> {
	public void add(T t);
	public void save(T t);
	public void update(T t);
	public void delete(T t);
}
