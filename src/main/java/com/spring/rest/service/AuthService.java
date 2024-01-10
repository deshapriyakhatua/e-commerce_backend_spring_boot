package com.spring.rest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.rest.exception.InvalidCredentialsException;
import com.spring.rest.jwt.JwtHelper;
import com.spring.rest.model.JwtRequest;
import com.spring.rest.model.JwtResponse;
import com.spring.rest.model.MyExceptionDetails;
import com.spring.rest.model.Role;
import com.spring.rest.model.User;
import com.spring.rest.repository.RoleRepository;
import com.spring.rest.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository customUserRepository;
	
	@Autowired
	private RoleRepository userRolesRepository;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private JwtHelper helper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public ResponseEntity<Object> signUp(User customUser) {
		System.out.println(1);
		if(customUser.getPassword().length() == 0) {
			return new ResponseEntity<>(new MyExceptionDetails("Empty password field !","uri=/auth/signUp"), HttpStatus.BAD_REQUEST);
		}
		
		customUser.setPassword(passwordEncoder.encode(customUser.getPassword()));
		Optional<User> optionalUser = customUserRepository.findByEmail(customUser.getEmail());
		System.out.println(10);
		if(optionalUser.isPresent()) {
			return new ResponseEntity<>(new MyExceptionDetails("User already registered !",HttpStatus.BAD_REQUEST.toString()), HttpStatus.BAD_REQUEST);
		}
			
		Role role = customUser.getRole();
		Role newRole = new Role();
			
		Optional<Role> optionalRole = userRolesRepository.findByRoleName(role.getRoleName());
		if(optionalRole.isEmpty()) {
			return new ResponseEntity<>(new MyExceptionDetails("User Role '" + role.getRoleName() + "' Not Available !", HttpStatus.BAD_REQUEST.toString()), HttpStatus.BAD_REQUEST);
		}
		newRole = optionalRole.get();
		
		
		customUser.setRole(newRole);
		customUserRepository.save(customUser);
		
		System.out.println(100);
		String token = helper.generateToken(customUser.getEmail());

		JwtResponse jwtResponse = new JwtResponse();
		jwtResponse.setJwtToken(token);
		jwtResponse.setUserName(customUser.getEmail());
		return new ResponseEntity<>(jwtResponse, HttpStatus.OK);

	}

	public ResponseEntity<JwtResponse> signIn(JwtRequest jwtRequest) {

		String email = jwtRequest.getEmail();
		String password = jwtRequest.getPassword();

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, password);

		try {
			authenticationProvider.authenticate(usernamePasswordAuthenticationToken);

		} catch (Exception e) {
			throw new InvalidCredentialsException("Invalid Username or Password  ! " + e);
		}

		String token = helper.generateToken(email);

		JwtResponse jwtResponse = new JwtResponse();
		jwtResponse.setJwtToken(token);
		jwtResponse.setUserName(email);
		return new ResponseEntity<>(jwtResponse, HttpStatus.OK);

	}

}
