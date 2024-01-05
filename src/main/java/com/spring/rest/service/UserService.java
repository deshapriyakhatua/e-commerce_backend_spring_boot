package com.spring.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.rest.model.UserProfile;
import com.spring.rest.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// saving a user in the database
	public ResponseEntity<UserProfile> saveUser(UserProfile user) {
		UserProfile newUser = userRepository.save(user);
		return ResponseEntity.ok(newUser);
	}

	// Get all users
	public ResponseEntity<List<UserProfile>> fetchAllUsers() {
		return ResponseEntity.ok(userRepository.findAll());
	}

	// Get a user by ID
	public ResponseEntity<UserProfile> fetchUserById(UUID id) {
		
		Optional<UserProfile> user = userRepository.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}

	// For updating a single user from the database
	public ResponseEntity<UserProfile> updateUser(UUID id, UserProfile updatedUser) {
		
		Optional<UserProfile> user = userRepository.findById(id);
		if (!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		UserProfile existingUser = user.get();
		existingUser.setName(updatedUser.getName());
		existingUser.setPhone(updatedUser.getPhone());
		UserProfile savedEntity = userRepository.save(existingUser);
		return ResponseEntity.ok(savedEntity);
	}

	// For deleting a single user from the database
	public ResponseEntity<String> deleteUser(UUID id) {
		
		Optional<UserProfile> user = userRepository.findById(id);
		if(user.isPresent()) {
			userRepository.delete(user.get());
			return ResponseEntity.ok("User Deleted Successfully against id " + id );
		}
		else {
			return ResponseEntity.ok("User Doesn't Exist");
		}
		
	}

}
