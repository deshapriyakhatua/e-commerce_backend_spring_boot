package com.spring.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.rest.model.User;
import com.spring.rest.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

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
	public ResponseEntity<Optional<User>> fetchUserById(UUID id) {
		if (id == null) {
			throw new IllegalArgumentException("ID cannot be null");
		}
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// For updating a single user from the database
	public ResponseEntity<User> updateUser(UUID id, User updatedUser) {
		if (id == null) {
			throw new IllegalArgumentException("ID cannot be null");
		}
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
		System.out.println(existingUser.getEmail() + " " + existingUser.getPassword());
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
		userRepository.deleteById(id);
		return ResponseEntity.ok("User Deleted Successfully");
	}

}
