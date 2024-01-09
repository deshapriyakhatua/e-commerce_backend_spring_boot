package com.spring.rest.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spring.rest.model.UserRoles;
import com.spring.rest.service.UserRolesService;

@RestController
@RequestMapping("/editor")
public class UserRoleController {

	@Autowired
	private final UserRolesService userRolesService;

	public UserRoleController(UserRolesService userRolesService) {
		this.userRolesService = userRolesService;
	}
	
	@PostMapping("/role")
	public ResponseEntity<UserRoles> saveRole(@RequestBody UserRoles userRole) {
		UserRoles newRoles = userRolesService.saveRole(userRole);
		return ResponseEntity.ok(newRoles);
	}
	
	@PostMapping("/roles")
	public ResponseEntity<List<UserRoles>> saveRoles(@RequestBody Set<UserRoles> userRoles) {
		List<UserRoles> newRoles = userRolesService.saveRoles(userRoles);
		return ResponseEntity.ok(newRoles);
	}
	
	@GetMapping("/role/{roleName}")
	public ResponseEntity<UserRoles> getRole(@PathVariable String roleName) {
		UserRoles newRoles = userRolesService.getRole(roleName);
		return ResponseEntity.ok(newRoles);
	}
	
	@GetMapping("/roles")
	public ResponseEntity<List<UserRoles>> getRoles() {
		List<UserRoles> newRoles = userRolesService.getRoles();
		return ResponseEntity.ok(newRoles);
	}
}
