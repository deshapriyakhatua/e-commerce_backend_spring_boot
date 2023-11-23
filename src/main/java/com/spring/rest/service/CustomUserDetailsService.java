package com.spring.rest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.rest.model.CustomUser;
import com.spring.rest.model.CustomUserDetails;
import com.spring.rest.repository.CustomUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	CustomUserRepository customUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<CustomUser> customUser = customUserRepository.findByEmail(username);
		return customUser.map(CustomUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found" + username));

	}

}
