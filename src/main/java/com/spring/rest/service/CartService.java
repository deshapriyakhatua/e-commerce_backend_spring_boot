package com.spring.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.rest.model.Product;
import com.spring.rest.model.Cart;
import com.spring.rest.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository userCartRepository;



	public ResponseEntity<List<Cart>> fetchAllCarts() {
		return ResponseEntity.ok(userCartRepository.findAll());
	}
	

	public ResponseEntity<Cart> fetchCartById(UUID id) {
		
		Optional<Cart> user = userCartRepository.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}

	public ResponseEntity<Cart> addProductToCart(UUID id, Set<Product> products) {
		
		Optional<Cart> cart = userCartRepository.findById(id);
		if (!cart.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cart existingCart = cart.get();
		existingCart.setProducts(products);
		Cart savedEntity = userCartRepository.save(existingCart);
		return ResponseEntity.ok(savedEntity);
		
	}
	
	public ResponseEntity<Cart> updateCart(UUID id, Cart updatedCart) {
		
		Optional<Cart> cart = userCartRepository.findById(id);
		if (!cart.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cart existingCart = cart.get();
		existingCart.setQuantity(updatedCart.getQuantity());
		Cart savedEntity = userCartRepository.save(existingCart);
		return ResponseEntity.ok(savedEntity);
	}
	
}
