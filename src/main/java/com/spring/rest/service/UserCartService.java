package com.spring.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.spring.rest.model.UserCart;
import com.spring.rest.repository.UserCartRepository;

@Service
public class UserCartService {

	@Autowired
	private UserCartRepository userCartRepository;



	public ResponseEntity<List<UserCart>> fetchAllCarts() {
		return ResponseEntity.ok(userCartRepository.findAll());
	}
	

	public ResponseEntity<UserCart> fetchCartById(UUID id) {
		
		Optional<UserCart> user = userCartRepository.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}

	
	public ResponseEntity<UserCart> updateCart(UUID id, UserCart updatedCart) {
		
		Optional<UserCart> cart = userCartRepository.findById(id);
		if (!cart.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		UserCart existingCart = cart.get();
		existingCart.setQuantity(updatedCart.getQuantity());
		UserCart savedEntity = userCartRepository.save(existingCart);
		return ResponseEntity.ok(savedEntity);
	}
	
}
