package com.spring.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spring.rest.model.UserRoles;
import com.spring.rest.repository.UserRolesRepository;

@RestController
@RequestMapping("/admi")
public class RoleControler {

	private final UserRolesRepository userRolesRepository;

	public RoleControler(UserRolesRepository userRolesRepository) {
		this.userRolesRepository = userRolesRepository;
	}
	
	@PostMapping("/roles")
	public ResponseEntity<UserRoles> saveUser(@RequestBody UserRoles userRoles) {
		UserRoles newRoles = userRolesRepository.save(userRoles);
		return ResponseEntity.ok(newRoles);
	}
	
}
