package com.spring.rest.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spring.rest.model.UserAddress;
import com.spring.rest.service.UserAddressService;

@RestController
@RequestMapping("/address")
public class UserAddressController {

	@Autowired
	UserAddressService userAddressService;

	@GetMapping("/addresses")
	public ResponseEntity<List<UserAddress>> getAllAddresses() {
		return userAddressService.fetchAllAddresses();
	}

	@GetMapping("/address/{userId}")
	public ResponseEntity<UserAddress> getAddressById(@PathVariable UUID userId) {
		return userAddressService.fetchAddressById(userId);
	}

	@PutMapping(path = "/address/{userId}")
	public ResponseEntity<UserAddress> updateAddress(@PathVariable UUID userId, @RequestBody UserAddress user) {
		return userAddressService.updateAddress(userId, user);
	}
	
}
