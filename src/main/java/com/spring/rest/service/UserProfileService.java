package com.spring.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.rest.model.UserProfile;
import com.spring.rest.repository.UserProfileRepository;

@Service
public class UserProfileService {
	
	@Autowired
	private UserProfileRepository userProfileRepository;



	public ResponseEntity<List<UserProfile>> fetchAllUsers() {
		return ResponseEntity.ok(userProfileRepository.findAll());
	}
	

	public ResponseEntity<UserProfile> fetchUserById(UUID id) {
		
		Optional<UserProfile> user = userProfileRepository.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}

	
	public ResponseEntity<UserProfile> updateUser(UUID id, UserProfile updatedUser) {
		
		Optional<UserProfile> user = userProfileRepository.findById(id);
		if (!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		UserProfile existingUser = user.get();
		existingUser.setName(updatedUser.getName());
		existingUser.setPhone(updatedUser.getPhone());
		UserProfile savedEntity = userProfileRepository.save(existingUser);
		return ResponseEntity.ok(savedEntity);
	}

	

}
