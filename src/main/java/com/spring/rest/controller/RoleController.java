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
import com.spring.rest.model.Role;
import com.spring.rest.service.RoleService;

@RestController
@RequestMapping("/tester")
public class RoleController {

	@Autowired
	private final RoleService userRolesService;

	public RoleController(RoleService userRolesService) {
		this.userRolesService = userRolesService;
	}
	
	@PostMapping("/role")
	public ResponseEntity<Role> saveRole(@RequestBody Role userRole) {
		Role newRoles = userRolesService.saveRole(userRole);
		return ResponseEntity.ok(newRoles);
	}
	
	@PostMapping("/roles")
	public ResponseEntity<List<Role>> saveRoles(@RequestBody Set<Role> userRoles) {
		List<Role> newRoles = userRolesService.saveRoles(userRoles);
		return ResponseEntity.ok(newRoles);
	}
	
	@GetMapping("/role/{roleName}")
	public ResponseEntity<Role> getRole(@PathVariable String roleName) {
		Role newRoles = userRolesService.getRole(roleName);
		return ResponseEntity.ok(newRoles);
	}
	
	@GetMapping("/roles")
	public ResponseEntity<List<Role>> getRoles() {
		List<Role> newRoles = userRolesService.getRoles();
		return ResponseEntity.ok(newRoles);
	}
}
