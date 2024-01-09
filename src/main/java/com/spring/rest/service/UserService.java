package com.spring.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.rest.model.CustomUser;
import com.spring.rest.repository.CustomUserRepository;

@Service
public class UserService {

	@Autowired
	private CustomUserRepository customUserRepository;
	
	public ResponseEntity<CustomUser> getUser(String email) {
		Optional<CustomUser> result = customUserRepository.findByEmail(email);
		CustomUser user = new CustomUser();
		if(result.isPresent()) {
			user = result.get();
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	public ResponseEntity<List<CustomUser>> getUsers() {
		List<CustomUser> users = customUserRepository.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
}
