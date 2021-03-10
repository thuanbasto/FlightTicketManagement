package com.tomcat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tomcat.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findOneByUsername(String username);
	@Query(value = "SELECT u.*,r.Role_Id,r.Name FROM user u " + 
			"INNER JOIN role_user ru ON u.User_Id = ru.User_Id " + 
			"INNER JOIN role r ON r.Role_Id = ru.Role_Id;"
			,nativeQuery=true)
	List<Object[]> getUsers();
}
