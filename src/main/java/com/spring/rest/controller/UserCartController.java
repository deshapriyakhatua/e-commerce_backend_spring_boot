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
import com.spring.rest.model.UserCart;
import com.spring.rest.service.UserCartService;

@RestController
@RequestMapping("/cart")
public class UserCartController {

	@Autowired
	private UserCartService userCartService;

	@GetMapping("/carts")
	public ResponseEntity<List<UserCart>> getAllUsers() {
		return userCartService.fetchAllCarts();
	}

	@GetMapping("/cart/{cartId}")
	public ResponseEntity<UserCart> getUserById(@PathVariable UUID cartId) {
		return userCartService.fetchCartById(cartId);
	}

	@PutMapping(path = "/cart/{cartId}")
	public ResponseEntity<UserCart> updateUser(@PathVariable UUID cartId, @RequestBody UserCart cart) {
		return userCartService.updateCart(cartId, cart);
	}

}
