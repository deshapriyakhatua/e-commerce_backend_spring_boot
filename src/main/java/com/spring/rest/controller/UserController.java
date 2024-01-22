package com.spring.rest.controller;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	@GetMapping("/current_user")
	public Object getCurretUser() {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		HashMap<String, String> user = new HashMap<String, String>();
		user.put("userName", loggedInUser.getName());
		String role = "";
		for(GrantedAuthority gta : loggedInUser.getAuthorities()) {
			role += "," + gta.getAuthority().substring(5);
		}
		user.put("role", role.substring(1));
		return user;
	}
	
	@GetMapping("/is_seller")
	public boolean isSeller() {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		boolean isTrue = false;
		for(GrantedAuthority gta : loggedInUser.getAuthorities()) {
			if(gta.getAuthority().equals("ROLE_SELLER")) {
				isTrue = true;
				break;
			}
		}
		return isTrue;
	}
	
	@GetMapping("/user/{userName}")
	public ResponseEntity<User> getUser(@PathVariable String userName) {
		return userService.getUser(userName);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		return userService.getUsers();
	}
	
}
