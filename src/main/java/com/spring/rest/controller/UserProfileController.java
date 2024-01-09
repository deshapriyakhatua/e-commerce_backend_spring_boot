package com.spring.rest.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.model.UserProfile;
import com.spring.rest.service.UserProfileService;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

	@Autowired
	private UserProfileService userService;

	@GetMapping("/users")
	public ResponseEntity<List<UserProfile>> getAllUsers() {
		return userService.fetchAllUsers();
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<UserProfile> getUserById(@PathVariable UUID userId) {
		return userService.fetchUserById(userId);
	}

	@PutMapping(path = "/user/{userId}")
	public ResponseEntity<UserProfile> updateUser(@PathVariable UUID userId, @RequestBody UserProfile user) {
		return userService.updateUser(userId, user);
	}

}
