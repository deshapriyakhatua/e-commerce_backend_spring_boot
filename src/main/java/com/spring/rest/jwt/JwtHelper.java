package com.spring.rest.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtHelper {

	private final String SECRET = "zASFaliACAsfaSDFAnteCASDFASFalkiASFDAFltqwiASFAaSgDhAbAhDqSCzSDtFADrCVSwGCFeVtADXrCm";
	public final long JWT_TOKEN_VALIDITY = 10 * 60 * 60 * 1000;

	public String generateToken(String userName) {
		
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder().setClaims(claims).setSubject(userName).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS512).compact();
	}

	public String getUsernameFromToken(String token) {
		
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Boolean validateToken(String token) {
		
		final Date expiration = getClaimFromToken(token, Claims::getExpiration);
		return expiration.after(new Date());
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		
		final Claims claims = Jwts.parserBuilder().setSigningKey(SECRET.getBytes()).build().parseClaimsJws(token)
				.getBody();
		return claimsResolver.apply(claims);
	}

}
