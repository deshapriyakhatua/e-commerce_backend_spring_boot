package com.spring.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.rest.model.Product;
import com.spring.rest.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	// saving a product in the database
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		Product newProduct = productRepository.save(product);
		return ResponseEntity.ok(newProduct);
	}

	// Get all products
	public ResponseEntity<List<Product>> fetchAllProducts() {
		return ResponseEntity.ok(productRepository.findAll());
	}

	// Get a product by ID
	public ResponseEntity<Optional<Product>> fetchProductById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// For updating a single product from the database
	public ResponseEntity<Product> updateProduct(Long id, Product updatedProduct) {
		if (id == null) {
			throw new IllegalArgumentException("ID cannot be null");
		}
		Product Existingproduct = productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
		Existingproduct.setName(updatedProduct.getName());
		Existingproduct.setPrice(updatedProduct.getPrice());
		Existingproduct.setQuantity(updatedProduct.getQuantity());
		Product savedEntity = productRepository.save(Existingproduct);
		return ResponseEntity.ok(savedEntity);
	}

	// For deleting a single product from the database
	public ResponseEntity<String> deleteProduct(Long id) {
		productRepository.deleteById(id);
		return ResponseEntity.ok("Product Deleted Successfully");
	}

}
