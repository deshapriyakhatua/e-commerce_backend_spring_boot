package com.spring.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.rest.model.User;
import com.spring.rest.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// saving a user in the database
	public ResponseEntity<User> saveUser(User user) {
		User newUser = userRepository.save(user);
		return ResponseEntity.ok(newUser);
	}

	// Get all users
	public ResponseEntity<List<User>> fetchAllUsers() {
		return ResponseEntity.ok(userRepository.findAll());
	}

	// Get a user by ID
	public ResponseEntity<User> fetchUserById(UUID id) {
		
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}

	// For updating a single user from the database
	public ResponseEntity<User> updateUser(UUID id, User updatedUser) {
		
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		User existingUser = user.get();
		existingUser.setName(updatedUser.getName());
		existingUser.setPhone(updatedUser.getPhone());
		existingUser.setGender(updatedUser.getGender());
		existingUser.setProfession(updatedUser.getProfession());
		existingUser.setAddress(updatedUser.getAddress());
		existingUser.setLatitude(updatedUser.getLatitude());
		existingUser.setLongitude(updatedUser.getLongitude());
		existingUser.setProfilePic(updatedUser.getProfilePic());
		existingUser.setCoverPic(updatedUser.getCoverPic());
		User savedEntity = userRepository.save(existingUser);
		return ResponseEntity.ok(savedEntity);
	}

	// For deleting a single user from the database
	public ResponseEntity<String> deleteUser(UUID id) {
		
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			userRepository.delete(user.get());
			return ResponseEntity.ok("User Deleted Successfully against id " + id );
		}
		else {
			return ResponseEntity.ok("User Doesn't Exist");
		}
		
	}

}
