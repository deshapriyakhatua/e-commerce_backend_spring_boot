package com.spring.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.rest.model.Address;
import com.spring.rest.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository userAddressRepository;



	public ResponseEntity<List<Address>> fetchAllAddresses() {
		return ResponseEntity.ok(userAddressRepository.findAll());
	}
	

	public ResponseEntity<Address> fetchAddressById(UUID id) {
		
		Optional<Address> user = userAddressRepository.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}

	
	public ResponseEntity<Address> updateAddress(UUID id, Address updatedAddress) {
		
		Optional<Address> address = userAddressRepository.findById(id);
		if (!address.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Address existingAddress = address.get();
		existingAddress.setCity(updatedAddress.getCity());
		Address savedEntity = userAddressRepository.save(existingAddress);
		return ResponseEntity.ok(savedEntity);
	}

	
}
