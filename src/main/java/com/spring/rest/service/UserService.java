package com.spring.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.rest.model.User;
import com.spring.rest.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository customUserRepository;
	
	public ResponseEntity<User> getUser(String email) {
		Optional<User> result = customUserRepository.findByEmail(email);
		User user = new User();
		if(result.isPresent()) {
			user = result.get();
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = customUserRepository.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
}
