package com.spring.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spring.rest.model.User;
import com.spring.rest.service.UserService;

@RestController
@RequestMapping("/account")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/user/{userName}")
	public ResponseEntity<User> getRole(@PathVariable String userName) {
		return userService.getUser(userName);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getRoles() {
		return userService.getUsers();
	}
	
}
