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
import jakarta.persistence.JoinTable;
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
	private double price;
	
	@Column(nullable = false)
	private double mrp;
	
	@Column(nullable = false)
	private double deliveryChrgs;
	
	@Column(nullable = true)
	private String description;
	
	@Column(nullable = true)
	private String specification;
	
	@Column(nullable = true)
	private double rating;
	
	@Column(nullable = true)
	private int countRatings;
	
	@Column(nullable = true)
	private int countReviews;
	
	@Column(nullable = true)
	private int countOrders;
	
	@Column(nullable = false)
	private boolean inStock;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", nullable = false)
	@JsonIgnoreProperties("products")
	private Category category;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("product")
	private Set<Image> images;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "seller_id", nullable = false)
	@JsonIgnoreProperties("products")
	private User seller;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
            name = "carts",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
            )
	@JsonIgnoreProperties("cart")
	private Set<User> carts;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
            name = "wishlists",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
            )
	@JsonIgnoreProperties("wishList")
	private Set<User> wishLists;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("product")
	private Set<Reviews> reviews;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("product")
	private Set<Orders> orders;

	public Product(String title, double price, double mrp, double deliveryChrgs, double rating, int countRatings,
			int countReviews, int countOrders, boolean inStock, Set<Image> images, User seller) {
		super();
		this.title = title;
		this.price = price;
		this.mrp = mrp;
		this.deliveryChrgs = deliveryChrgs;
		this.rating = rating;
		this.countRatings = countRatings;
		this.countReviews = countReviews;
		this.countOrders = countOrders;
		this.inStock = inStock;
		this.seller = seller;
	}
	
	
	
}
