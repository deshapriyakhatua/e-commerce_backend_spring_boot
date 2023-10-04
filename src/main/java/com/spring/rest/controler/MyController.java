package com.spring.rest.controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/v1")
public class MyController {
	
	private final ProductService productService;

	public MyController(ProductService productService) {
		this.productService = productService;
	}

	// Save product
	@PostMapping("/product")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}

	// Get all products
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		return productService.fetchAllProducts();
	}

	// Get a product by ID
	@GetMapping("/products/{id}")
	public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id) {
		return productService.fetchProductById(id);
	}

	// Update a product
	@PutMapping(path = "/products/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value = "productId") Long productId,
			@RequestBody Product product) {
		return productService.updateProduct(productId, product);
	}

	// Delete a product
	@DeleteMapping(value = "/products/{productId}")
	public String deleteProduct(@PathVariable Long productId) {
		productService.deleteProduct(productId);
		return "Product Deleted Successfully against id " + productId + " ";
	}

}
