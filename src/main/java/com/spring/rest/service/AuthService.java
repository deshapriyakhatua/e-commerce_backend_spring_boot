package com.spring.rest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.spring.rest.exception.InvalidCredentialsException;
import com.spring.rest.jwt.JwtHelper;
import com.spring.rest.model.CustomUser;
import com.spring.rest.model.JwtRequest;
import com.spring.rest.model.JwtResponse;
import com.spring.rest.model.MyExceptionDetails;
import com.spring.rest.repository.CustomUserRepository;

@Service
public class AuthService {

	@Autowired
	private CustomUserRepository customUserRepository;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private JwtHelper helper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public ResponseEntity<Object> registerUser(CustomUser customUser) {

		if(customUser.getPassword().length() == 0) {
			return new ResponseEntity<>(new MyExceptionDetails("Empty password field !","uri=/auth/signUp"), HttpStatus.BAD_REQUEST);
		}
		customUser.setPassword(passwordEncoder.encode(customUser.getPassword()));
		Optional<CustomUser> optionalUser = customUserRepository.findByEmail(customUser.getEmail());
		if(!optionalUser.isPresent()) {
			customUserRepository.save(customUser);
		}
		else {
			return new ResponseEntity<>(new MyExceptionDetails(optionalUser.get().getEmail() + " already registered !","uri=/auth/signUp"), HttpStatus.BAD_REQUEST);
		}
		String token = helper.generateToken(customUser.getEmail());

		JwtResponse jwtResponse = new JwtResponse();
		jwtResponse.setJwtToken(token);
		jwtResponse.setUserName(customUser.getEmail());
		return new ResponseEntity<>(jwtResponse, HttpStatus.OK);

	}

	public ResponseEntity<JwtResponse> logInUser(JwtRequest jwtRequest) {

		String email = jwtRequest.getEmailId();
		String password = jwtRequest.getPassword();

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				email, password);

		try {
			authenticationProvider.authenticate(usernamePasswordAuthenticationToken);

		} catch (BadCredentialsException e) {
			throw new InvalidCredentialsException("Invalid Username or Password  !");
		}

		String token = helper.generateToken(email);

		JwtResponse jwtResponse = new JwtResponse();
		jwtResponse.setJwtToken(token);
		jwtResponse.setUserName(email);
		return new ResponseEntity<>(jwtResponse, HttpStatus.OK);

	}

}
