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

import com.spring.rest.model.Profile;
import com.spring.rest.service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private ProfileService userService;

	@GetMapping("/users")
	public ResponseEntity<List<Profile>> getAllUsers() {
		return userService.fetchAllUsers();
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<Profile> getUserById(@PathVariable UUID userId) {
		return userService.fetchUserById(userId);
	}

	@PutMapping(path = "/user/{userId}")
	public ResponseEntity<Profile> updateUser(@PathVariable UUID userId, @RequestBody Profile user) {
		return userService.updateUser(userId, user);
	}

}
