package com.spring.rest.model;


import java.util.Set;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_DEFAULT)
public class User {

	@Id
	@GeneratedValue
	private UUID userId;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	@JsonIgnoreProperties("users")
	private Role role;
	
	@Column(nullable = true)
	private long phone;

	@Column(nullable = true)
	private String name;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("user")
	private Set<Address> addresses;
	
	@ManyToMany(mappedBy = "carts", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("carts")
	private Set<Product> cart;
	
	@ManyToMany(mappedBy = "wishLists", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("wishLists")
	private Set<Product> wishList;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("user")
	private Set<Reviews> reviews;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"user","hibernateLazyInitializer", "handler"})
	private Set<Orders> orders;
	
	@OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("seller")
	private Set<Product> products;

	public User( String email, String password, Role role) {
		super();
		this.email = email;
		this.password = password;	
		this.role = role;
	}
	
	
	
}
