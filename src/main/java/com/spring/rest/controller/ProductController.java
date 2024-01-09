package com.spring.rest.controller;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spring.rest.model.Product;
import com.spring.rest.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/products")
	public ResponseEntity<List<Product>> saveAllProductss(@RequestBody Set<Product> products) {
		return productService.saveProducts(products);
	}

	@PostMapping("/product")
	public ResponseEntity<Product> saveUser(@RequestBody Product product) {
		return productService.saveProduct(product);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllUsers() {
		return productService.getProducts();
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> getUserById(@PathVariable UUID productId) {
		return productService.getProduct(productId);
	}

	@PutMapping(path = "/product")
	public ResponseEntity<Product> updateUser(@RequestBody Product product) {
		return productService.updateProduct(product);
	}
}
