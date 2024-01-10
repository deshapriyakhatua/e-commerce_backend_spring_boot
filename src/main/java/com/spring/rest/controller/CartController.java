package com.spring.rest.controller;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.model.Product;
import com.spring.rest.model.Cart;
import com.spring.rest.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService userCartService;

	@GetMapping("/carts")
	public ResponseEntity<List<Cart>> getAllUsers() {
		return userCartService.fetchAllCarts();
	}

	@GetMapping("/cart/{cartId}")
	public ResponseEntity<Cart> getUserById(@PathVariable UUID cartId) {
		return userCartService.fetchCartById(cartId);
	}

	@PutMapping(path = "/cart/{cartId}")
	public ResponseEntity<Cart> updateUser(@PathVariable UUID cartId, @RequestBody Cart cart) {
		return userCartService.updateCart(cartId, cart);
	}
	
	@PutMapping(path = "/products/{cartId}")
	public ResponseEntity<Cart> addProductToCart(@PathVariable UUID cartId, @RequestBody Set<Product> products) {
		return userCartService.addProductToCart(cartId, products);
	}

}
