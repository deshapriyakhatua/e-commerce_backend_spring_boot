package com.spring.rest.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.model.UserProfile;
import com.spring.rest.service.UserService;

@RestController
@RequestMapping("/api")
public class MyController {
	
	private final UserService userService;

	public MyController(UserService userService) {
		this.userService = userService;
	}

	// Save user
	@PostMapping("/users")
	public ResponseEntity<UserProfile> saveUser(@RequestBody UserProfile userProfile) {
		return userService.saveUser(userProfile);
	}

	// Get all users
	@GetMapping("/users")
	public ResponseEntity<List<UserProfile>> getAllUsers() {
		return userService.fetchAllUsers();
	}

	// Get a user by ID
	@GetMapping("/users/{userId}")
	public ResponseEntity<UserProfile> getUserById(@PathVariable UUID userId) {
		return userService.fetchUserById(userId);
	}

	// Update a user
	@PutMapping(path = "/users/{userId}")
	public ResponseEntity<UserProfile> updateUser(@PathVariable UUID userId,
			@RequestBody UserProfile user) {
		return userService.updateUser(userId, user);
	}

	// Delete a user
	@DeleteMapping(value = "/users/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable UUID userId) {
		return userService.deleteUser(userId);		
	}

}
