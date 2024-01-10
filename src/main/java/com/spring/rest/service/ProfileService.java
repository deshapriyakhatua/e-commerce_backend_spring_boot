package com.spring.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.rest.model.Profile;
import com.spring.rest.repository.ProfileRepository;

@Service
public class ProfileService {
	
	@Autowired
	private ProfileRepository userProfileRepository;



	public ResponseEntity<List<Profile>> fetchAllUsers() {
		return ResponseEntity.ok(userProfileRepository.findAll());
	}
	

	public ResponseEntity<Profile> fetchUserById(UUID id) {
		
		Optional<Profile> user = userProfileRepository.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}

	
	public ResponseEntity<Profile> updateUser(UUID id, Profile updatedUser) {
		
		Optional<Profile> user = userProfileRepository.findById(id);
		if (!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Profile existingUser = user.get();
		existingUser.setName(updatedUser.getName());
		existingUser.setPhone(updatedUser.getPhone());
		Profile savedEntity = userProfileRepository.save(existingUser);
		return ResponseEntity.ok(savedEntity);
	}

	

}
