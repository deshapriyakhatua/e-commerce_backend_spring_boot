package com.spring.rest.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.rest.model.MyExceptionDetails;
import com.spring.rest.service.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, java.io.IOException {

		System.out.println(request.getRequestURI());

		String authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer") || authorizationHeader.length() < 10) {

			sendException(response, request, HttpStatus.BAD_REQUEST.value(),
					"Access Denied ! No Authorization key in Header !");
			return;

		}

		String token = authorizationHeader.substring(7);
		String username;

		try {

			Boolean isValidToken = jwtHelper.validateToken(token);

			if (!isValidToken) {

				sendException(response, request, HttpStatus.BAD_REQUEST.value(),
						"Access Denied ! Token is not valid !");
				return;

			}

			username = jwtHelper.getUsernameFromToken(token);

		} catch (ExpiredJwtException e) {

			sendException(response, request, HttpStatus.BAD_REQUEST.value(), "Access Denied ! JWT token expired !");
			return;

		} catch (MalformedJwtException e) {

			sendException(response, request, HttpStatus.BAD_REQUEST.value(), "Access Denied ! Malformed JWT JSON !");
			return;

		} catch (SignatureException e) {

			sendException(response, request, HttpStatus.BAD_REQUEST.value(),
					"Access Denied ! JWT signature does not match locally computed signature !");
			return;

		} catch (Exception e) {

			sendException(response, request, HttpStatus.BAD_REQUEST.value(), "Access Denied ! Authentication failed !");
			return;

		}

		UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

		filterChain.doFilter(request, response);
		return;

	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

		return request.getRequestURI().startsWith("/auth/sign") || request.getRequestURI().startsWith("/tester/");

	}

	private void sendException(HttpServletResponse response, HttpServletRequest request, int httpStatus, String message)
			throws StreamWriteException, DatabindException, java.io.IOException {

		response.setStatus(httpStatus);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getWriter(), new MyExceptionDetails(message, request.getRequestURI()));

	}

}
