package com.spring.rest.model;

import java.util.Set;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_DEFAULT)
public class Product {

	@Id
	@GeneratedValue
	private UUID productId;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private Double price;
	
	@Column(nullable = false)
	private Double deliveryChrgs;
	
	@Column(nullable = true)
	private String description;
	
	@Column(nullable = true)
	private String specification;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", nullable = false)
	@JsonIgnoreProperties("products")
	private Category category;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("product")
	private Set<Image> images;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnoreProperties("products")
	private User user;
	
	@ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("products")
	private Set<Cart> carts;
	
	@ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("products")
	private Set<WishList> wishLists;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("product")
	private Set<Reviews> reviews;
	
	@ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("products")
	private Set<Orders> orders;
	
}
