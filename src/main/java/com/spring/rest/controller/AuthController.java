package com.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.model.CustomUser;
import com.spring.rest.model.CustomUserDetails;
import com.spring.rest.model.JwtRequest;
import com.spring.rest.model.JwtResponse;
import com.spring.rest.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/signUp")
	public ResponseEntity<Object> signUp(@RequestBody CustomUser customUser) {

		return authService.signUp(customUser);
	}

	@PostMapping("/signIn")
	public ResponseEntity<JwtResponse> signIn(@ModelAttribute JwtRequest jwtRequest) {

		return authService.signIn(jwtRequest);

	}
	
	@GetMapping("/current_user")
	public String getCurretUser() {
		
		CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return customUserDetails.getUsername();
		
	}

}
