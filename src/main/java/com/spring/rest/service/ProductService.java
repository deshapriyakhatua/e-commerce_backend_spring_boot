package com.spring.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.spring.rest.model.Product;
import com.spring.rest.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public ResponseEntity<List<Product>> saveProducts(Set<Product> products){
		return ResponseEntity.ok(productRepository.saveAll(products));
	}
	
	public ResponseEntity<Product> saveProduct(Product product){
		return ResponseEntity.ok(productRepository.save(product));
	}
	
	public ResponseEntity<Product> updateProduct(Product product){
		
		Optional<Product> result = productRepository.findById(product.getId());
		if(result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Product oldProduct = result.get();
		if(product.getTitle() != null) { oldProduct.setTitle(product.getTitle()); }
		if(product.getPrice() != null) { oldProduct.setPrice(product.getPrice()); }
		if(product.getDescription() != null) { oldProduct.setDescription(product.getDescription()); }
		return ResponseEntity.ok(productRepository.save(oldProduct));
	}
	
	public ResponseEntity<List<Product>> getProducts(){
		return ResponseEntity.ok(productRepository.findAll());
	}
	
	public ResponseEntity<Product> getProduct(UUID productId){
		Optional<Product> result = productRepository.findById(productId);
		if(result.isPresent()) {
			return ResponseEntity.ok(result.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
