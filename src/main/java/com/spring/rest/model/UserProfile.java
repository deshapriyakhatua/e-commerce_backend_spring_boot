package com.spring.rest.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
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
public class UserProfile {
	@Id
	@GeneratedValue
	private UUID id;

	@Column(nullable = true)
	private long phone;

	@Column(nullable = true)
	private String name;

	@OneToOne(mappedBy = "profile")
	@JsonIgnoreProperties("profile")
	private CustomUser user;

}
