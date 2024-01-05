package com.spring.rest.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
	@Id
	@GeneratedValue
	private UUID id;

	@Column(nullable = true)
	private String email;
	
	@Column(nullable = true)
	private long phone;

	@Column(nullable = true)
	private String name;


}
