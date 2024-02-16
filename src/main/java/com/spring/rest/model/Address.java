package com.spring.rest.model;

import java.util.Set;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue
	private UUID addressId;
	
	@Column(nullable = false)
	private String locality;
	
	@Column(nullable = false)
	private int pin;
	
	@Column(nullable = false)
	private String District;
	
	@Column(nullable = false)
	private String state;
	
	@Column(nullable = false)
	private String landmark;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties("addresses")
	private User user;
	
	@OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"address","hibernateLazyInitializer", "handler"})
	private Set<Orders> orders;
	
}
