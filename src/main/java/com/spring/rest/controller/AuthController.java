package com.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.model.CustomUser;
import com.spring.rest.model.JwtRequest;
import com.spring.rest.model.JwtResponse;
import com.spring.rest.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/signUp")
	public ResponseEntity<Object> signUp(@ModelAttribute CustomUser customUser) {

		System.out.println("signup controller called");
		return authService.registerUser(customUser);
	}

	@PostMapping("/logIn")
	public ResponseEntity<JwtResponse> logIn(@RequestBody JwtRequest jwtRequest) {

		System.out.println("login controller called");
		return authService.logInUser(jwtRequest);

	}

}
