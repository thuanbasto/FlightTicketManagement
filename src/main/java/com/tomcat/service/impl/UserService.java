package com.tomcat.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tomcat.converter.UserConverter;
import com.tomcat.dto.RoleDTO;
import com.tomcat.dto.UserDTO;
import com.tomcat.entity.User;
import com.tomcat.repository.RoleRepository;
import com.tomcat.repository.UserRepository;
import com.tomcat.service.IUserService;

@Service
public class UserService implements IUserService{

	@Autowired
	UserConverter userConverter;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Override
	public void save(UserDTO userDTO) {
		User user = userConverter.toUser(userDTO);
		userRepository.save(user);
	}

	@Override
	@Transactional
	public boolean generateAccountAdmin() {
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername("admin");
		userDTO.setPassword(passwordEncoder.encode("1"));
		userDTO.setEnable((byte) 1);
		userDTO.setRoleIdList(new ArrayList<String>(Arrays.asList("1","2")));
		
		if (getUser("admin") == null) { // if not exist generate admin account
			save(userDTO);
			return true;
		}
		return false;
	}

	@Override
	public List<UserDTO> getUsers() {
		List<Object[]> users = userRepository.getUsers();
		Map<Integer,UserDTO> userDTOs = new HashMap<Integer,UserDTO>();
		users.forEach(user -> {  // Distinct user
			Integer user_Id = Integer.valueOf(String.valueOf(user[0]));
			
			if (userDTOs.containsKey(user_Id)) { // add role
				UserDTO userDTO = userDTOs.get(user_Id);
				
				Set<RoleDTO> roleDTOs = userDTO.getRoles();
				roleDTOs.add(new RoleDTO(Integer.valueOf(String.valueOf(user[10])),String.valueOf(user[11])));
				userDTO.setRoles(roleDTOs);
				
				userDTOs.put(user_Id, userDTO);
			}
			else 
				userDTOs.put(user_Id,userConverter.toDTO(user));
				
		});
		
		List<UserDTO> list = new ArrayList<UserDTO>(userDTOs.values());
		return list;
	}

	@Override
	@Transactional
	public UserDTO getUser(String input) {
		User user;
		try { // check either id or username
			// check id
			Integer id = Integer.parseInt(input);
			user = userRepository.findOne(id);
		} catch (NumberFormatException e) {
			// check username
			user = userRepository.findOneByUsername(input);
		}
		
		if (user == null) return null;
		
		UserDTO userDTO = userConverter.toDTO(user);
		return userDTO;
	}

	@Override
	public void delete(String id) {
		userRepository.delete(Integer.valueOf(id));
	}
	
	
	
}
