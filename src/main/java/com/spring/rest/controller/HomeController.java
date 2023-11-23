package com.spring.rest.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spring.rest.model.CustomUserDetails;

@RestController
@RequestMapping("/home")
public class HomeController {

	@GetMapping("/user")
	public String getUser() {
		System.out.println("/user controller called");
		return "user";
	}

	@GetMapping("/current_user")
	public String getCurretUser() {
		// System.out.println(principal);
		CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return customUserDetails.getUsername();
	}
}
