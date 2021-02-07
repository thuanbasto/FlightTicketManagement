package com.tomcat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tomcat.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	
}
