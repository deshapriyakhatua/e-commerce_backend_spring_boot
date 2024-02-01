package com.spring.rest.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.spring.rest.model.WishList;
import com.spring.rest.repository.WishListRepository;

@Service
public class WishListService {

	@Autowired
	private WishListRepository wishListRepository;

	public ResponseEntity<WishList> getWishListById(UUID id) {
		return ResponseEntity.ok(wishListRepository.findById(id).get());
	}
	
}
