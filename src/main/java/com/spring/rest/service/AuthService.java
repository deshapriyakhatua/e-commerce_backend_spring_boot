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
import com.spring.rest.model.Cart;
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
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository userRolesRepository;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private JwtHelper helper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public ResponseEntity<Object> signUp(User user) {
		
		if(user.getEmail() == null || user.getEmail().length() == 0) {
			return new ResponseEntity<>(new MyExceptionDetails("Empty Email field !", "uri=/auth/signUp"), HttpStatus.BAD_REQUEST);
		}
		if(user.getPassword() == null || user.getPassword().length() == 0) {
			return new ResponseEntity<>(new MyExceptionDetails("Empty password field !", "uri=/auth/signUp"), HttpStatus.BAD_REQUEST);
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
		
		if(optionalUser.isPresent()) {
			return new ResponseEntity<>(new MyExceptionDetails("User already registered !", HttpStatus.BAD_REQUEST.toString()), HttpStatus.BAD_REQUEST);
		}
		
		// validate Role
		Role newRole = new Role();
			
		Optional<Role> optionalRole = userRolesRepository.findByRoleName("ROLE_ADMIN");
		if(optionalRole.isEmpty()) {
			return new ResponseEntity<>(new MyExceptionDetails("User Role 'ROLE_USER' Not Available !", HttpStatus.BAD_REQUEST.toString()), HttpStatus.BAD_REQUEST);
		}
		
		newRole = optionalRole.get();
		user.setRole(newRole);
		
		user.setCart(new Cart());
		
		userRepository.save(user);
		
		
		String token = helper.generateToken(user.getEmail());

		JwtResponse jwtResponse = new JwtResponse();
		jwtResponse.setJwtToken(token);
		jwtResponse.setUserName(user.getEmail());
		return new ResponseEntity<>(jwtResponse, HttpStatus.OK);

	}

	public ResponseEntity<Object> signIn(JwtRequest jwtRequest) {

		String email = jwtRequest.getEmail();
		String password = jwtRequest.getPassword();
		
		if(email == null || email.length() == 0) {
			return new ResponseEntity<>(new MyExceptionDetails("Empty Email field !", "uri=/auth/signUp"), HttpStatus.BAD_REQUEST);
		}
		if(password == null || password.length() == 0) {
			return new ResponseEntity<>(new MyExceptionDetails("Empty password field !", "uri=/auth/signUp"), HttpStatus.BAD_REQUEST);
		}

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
