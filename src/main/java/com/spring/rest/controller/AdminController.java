package com.spring.rest.controller;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.model.Category;
import com.spring.rest.model.Role;
import com.spring.rest.service.CategoryService;
import com.spring.rest.service.RoleService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private RoleService userRolesService;
	@Autowired
	private CategoryService categoryService;
	
	// role controllers
	
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
	
	// category controllers

	@PostMapping("/categories")
	public ResponseEntity<List<Category>> saveAllCategories(@RequestBody Set<Category> categorys) {
		return categoryService.saveCategories(categorys);
	}

	@PostMapping("/category")
	public ResponseEntity<Object> saveUser(@RequestBody Category category) {
		return categoryService.saveCategory(category);
	}
	
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllUsers() {
		return categoryService.getCategories();
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<Category> getUserById(@PathVariable UUID categoryId) {
		return categoryService.getCategory(categoryId);
	}

	@PutMapping(path = "/category")
	public ResponseEntity<Category> updateUser(@RequestBody Category category) {
		return categoryService.updateCategory(category);
	}
}
