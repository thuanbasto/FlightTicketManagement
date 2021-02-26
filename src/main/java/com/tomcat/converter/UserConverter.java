package com.tomcat.converter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.tomcat.dto.RoleDTO;
import com.tomcat.dto.UserDTO;
import com.tomcat.entity.Role;
import com.tomcat.entity.User;
import com.tomcat.enums.RoleEnum;
import com.tomcat.repository.RoleRepository;

@Component
public class UserConverter {
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	RoleConverter roleConverter;
	
	public User toUser(UserDTO userDTO) {
		// lock account
		if (userDTO.getEnable() == null) userDTO.setEnable((byte) 0);
		// lowercase for username
		userDTO.setUsername(userDTO.getUsername().toLowerCase());
		
		User user = mapper.map(userDTO, User.class);
		
		// set roles 
		Set<Role> roles = new HashSet<Role>();
		if (userDTO.getRoleIdList() != null && userDTO.getRoleIdList().size() > 0) {
			userDTO.getRoleIdList().forEach(role_Id -> roles.add(roleRepository.findOne(Integer.valueOf(role_Id))));
		} else {
			// if null set role default for user is ROLE_STAFF
			roles.add(roleRepository.findOne(RoleEnum.ROLE_STAFF.getId()));
		}
		user.setRoles(roles);
		
		return user;
	}
	
	public UserDTO toDTO(User user) {
		UserDTO userDTO = new UserDTO();
		
		userDTO.setUser_Id(user.getUser_Id());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setBirthDay(user.getBirthDay());
		userDTO.setAddress(user.getAddress());
		userDTO.setPhone(user.getPhone());
		userDTO.setEmail(user.getEmail());
		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		userDTO.setEnable(user.getEnable());
		userDTO.setRoles(roleConverter.toRoleDTO(Lists.newArrayList(user.getRoles())));
		
		return userDTO;
	}
	
	public UserDTO toDTO(Object[] obj) {
		UserDTO userDTO = new UserDTO();
		
		userDTO.setUser_Id(Integer.valueOf(String.valueOf(obj[0])));
		userDTO.setFirstName(String.valueOf(obj[1]));
		userDTO.setLastName(String.valueOf(obj[2]));
		userDTO.setBirthDay((Date) obj[3]);
		userDTO.setAddress(String.valueOf(obj[4]));
		userDTO.setPhone(String.valueOf(obj[5]));
		userDTO.setEmail(String.valueOf(obj[6]));
		userDTO.setUsername(String.valueOf(obj[7]));
		userDTO.setPassword(String.valueOf(obj[8]));
		
		if ("true".equals(String.valueOf(obj[9])))
			userDTO.setEnable((byte) 1);
		else 
			userDTO.setEnable((byte) 0);
			
		// convert role
		RoleDTO roleDTO = new RoleDTO(Integer.valueOf(String.valueOf(obj[10])),String.valueOf(obj[11]));
		Set<RoleDTO> roles = new HashSet<RoleDTO>();
		roles.add(roleDTO); 
		
		userDTO.setRoles(roles);
		
		return userDTO;
	}
}
