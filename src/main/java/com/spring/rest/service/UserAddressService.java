package com.spring.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.spring.rest.model.UserAddress;
import com.spring.rest.repository.UserAddressRepository;

public class UserAddressService {

	@Autowired
	private UserAddressRepository userAddressRepository;



	public ResponseEntity<List<UserAddress>> fetchAllAddresses() {
		return ResponseEntity.ok(userAddressRepository.findAll());
	}
	

	public ResponseEntity<UserAddress> fetchAddressById(UUID id) {
		
		Optional<UserAddress> user = userAddressRepository.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}

	
	public ResponseEntity<UserAddress> updateAddress(UUID id, UserAddress updatedAddress) {
		
		Optional<UserAddress> address = userAddressRepository.findById(id);
		if (!address.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		UserAddress existingAddress = address.get();
		existingAddress.setCity(updatedAddress.getCity());
		UserAddress savedEntity = userAddressRepository.save(existingAddress);
		return ResponseEntity.ok(savedEntity);
	}

	
}
