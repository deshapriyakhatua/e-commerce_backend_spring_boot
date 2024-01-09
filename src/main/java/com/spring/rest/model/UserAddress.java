package com.spring.rest.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@OneToOne(mappedBy = "address")
	@JsonIgnoreProperties("address")
	private CustomUser user;
	
}
