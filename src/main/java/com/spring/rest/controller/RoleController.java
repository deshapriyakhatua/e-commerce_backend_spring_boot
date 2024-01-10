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
import com.spring.rest.model.Roles;
import com.spring.rest.service.RolesService;

@RestController
@RequestMapping("/tester")
public class RoleController {

	@Autowired
	private final RolesService userRolesService;

	public RoleController(RolesService userRolesService) {
		this.userRolesService = userRolesService;
	}
	
	@PostMapping("/role")
	public ResponseEntity<Roles> saveRole(@RequestBody Roles userRole) {
		Roles newRoles = userRolesService.saveRole(userRole);
		return ResponseEntity.ok(newRoles);
	}
	
	@PostMapping("/roles")
	public ResponseEntity<List<Roles>> saveRoles(@RequestBody Set<Roles> userRoles) {
		List<Roles> newRoles = userRolesService.saveRoles(userRoles);
		return ResponseEntity.ok(newRoles);
	}
	
	@GetMapping("/role/{roleName}")
	public ResponseEntity<Roles> getRole(@PathVariable String roleName) {
		Roles newRoles = userRolesService.getRole(roleName);
		return ResponseEntity.ok(newRoles);
	}
	
	@GetMapping("/roles")
	public ResponseEntity<List<Roles>> getRoles() {
		List<Roles> newRoles = userRolesService.getRoles();
		return ResponseEntity.ok(newRoles);
	}
}
