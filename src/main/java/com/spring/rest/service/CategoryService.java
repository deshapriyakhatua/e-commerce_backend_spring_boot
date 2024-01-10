package com.spring.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.spring.rest.model.Category;
import com.spring.rest.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public ResponseEntity<List<Category>> saveCategories(Set<Category> categories){
		return ResponseEntity.ok(categoryRepository.saveAll(categories));
	}
	
	public ResponseEntity<Category> saveCategory(Category category){
		return ResponseEntity.ok(categoryRepository.save(category));
	}
	
	public ResponseEntity<Category> updateCategory(Category category){
		
		Optional<Category> result = categoryRepository.findById(category.getCategoryId());
		if(result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Category oldCategory = result.get();
		if(category.getCategoryName() != null) { oldCategory.setCategoryName(category.getCategoryName()); }
		return ResponseEntity.ok(categoryRepository.save(oldCategory));
	}
	
	public ResponseEntity<List<Category>> getCategories(){
		return ResponseEntity.ok(categoryRepository.findAll());
	}
	
	public ResponseEntity<Category> getCategory(UUID categoryId){
		Optional<Category> result = categoryRepository.findById(categoryId);
		if(result.isPresent()) {
			return ResponseEntity.ok(result.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
