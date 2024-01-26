package com.spring.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.rest.model.Category;
import com.spring.rest.model.MyExceptionDetails;
import com.spring.rest.model.Product;
import com.spring.rest.repository.CategoryRepository;
import com.spring.rest.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public ResponseEntity<List<Product>> saveProducts(Set<Product> products){
		return ResponseEntity.ok(productRepository.saveAll(products));
	}
	
	public ResponseEntity<Object> saveProduct(Product product){
		
		Category category = product.getCategory();
		if(category.getCategoryName() == null || category.getCategoryName().length() == 0) {
			return new ResponseEntity<>(new MyExceptionDetails("Empty category name field !", "uri=/product/product"), HttpStatus.BAD_REQUEST);
		}
		
		Optional<Category> optionalCategory = categoryRepository.findByCategoryName(category.getCategoryName());
		
		if(optionalCategory.isEmpty()) {
			return new ResponseEntity<>(new MyExceptionDetails("'" + category.getCategoryName() + "' is not available for now try with another !", "uri=/product/product"), HttpStatus.BAD_REQUEST);
		}
		product.setCategory(optionalCategory.get());
		
		return ResponseEntity.ok(productRepository.save(product));
	}
	
	public ResponseEntity<Product> updateProduct(Product product){
		
		Optional<Product> result = productRepository.findById(product.getProductId());
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
