package com.spring.rest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.rest.model.User;
import com.spring.rest.model.CustomUserDetails;
import com.spring.rest.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository customUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> customUser = customUserRepository.getByEmail(username);
		return customUser.map(CustomUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found" + username));

	}

}
