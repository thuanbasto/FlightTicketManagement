package com.tomcat.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.dto.UserDTO;
import com.tomcat.service.IUserService;

@RestController
@RequestMapping("/api")
public class UserAPI {
	
	@Autowired
	IUserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/users")
	public ResponseEntity<List<UserDTO>> getUsers(){
		List<UserDTO> userDTOs = userService.getUsers();
		if (userDTOs.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(userDTOs,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('STAFF','ADMIN')")
	@GetMapping(value = "/users/{idOrUsername}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("idOrUsername") String input){
		UserDTO userDTO = userService.getUser(input);
		if(userDTO == null) 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/users")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO){
		UserDTO _userDTO = userService.getUser(userDTO.getUsername());
		if(_userDTO != null) 
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		userService.save(userDTO);
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/users/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable String id){
		userService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/users/{id}")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable("id") String id) {
		UserDTO _userDTO = userService.getUser(id);
		if(_userDTO == null) 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		// encoder password
		if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) 
			userDTO.setPassword(_userDTO.getPassword());
		else 
			userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		userService.save(userDTO);
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
	
}
