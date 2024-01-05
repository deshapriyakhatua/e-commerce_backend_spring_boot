package com.spring.rest.model;

import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAddress {

	@Id
	@GeneratedValue
	private UUID id;
	
	private String city;
	
	@OneToOne(cascade=CascadeType.ALL)
	private UserProfile userProfile;
}
