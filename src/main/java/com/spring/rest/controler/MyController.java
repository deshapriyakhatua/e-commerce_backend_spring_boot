package com.spring.rest.controler;

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

import com.spring.rest.model.User;
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
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	// Get all users
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		return userService.fetchAllUsers();
	}

	// Get a user by ID
	@GetMapping("/users/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable UUID userId) {
		return userService.fetchUserById(userId);
	}

	// Update a user
	@PutMapping(path = "/users/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable UUID userId,
			@RequestBody User user) {
		return userService.updateUser(userId, user);
	}

	// Delete a user
	@DeleteMapping(value = "/users/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable UUID userId) {
		return userService.deleteUser(userId);		
	}

}
