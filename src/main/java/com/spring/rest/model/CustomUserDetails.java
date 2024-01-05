package com.spring.rest.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

	static final long serialVersionUID = 1L;

	private String email;
	private String password;
	private List<GrantedAuthority> authorities;

	public CustomUserDetails(CustomUser customUser) {
		email = customUser.getEmail();
		password = customUser.getPassword();
		authorities = Arrays.stream(customUser.getRoles().toArray(new String[0])).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		System.out.println(authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
