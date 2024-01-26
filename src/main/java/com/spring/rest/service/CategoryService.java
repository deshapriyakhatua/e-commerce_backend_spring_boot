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
import com.spring.rest.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public ResponseEntity<List<Category>> saveCategories(Set<Category> categories){
		return ResponseEntity.ok(categoryRepository.saveAll(categories));
	}
	
	public ResponseEntity<Object> saveCategory(Category category){
		
		if(category.getCategoryName() == null) {
			return new ResponseEntity<>(new MyExceptionDetails("Category name is null !",HttpStatus.BAD_REQUEST.toString()), HttpStatus.BAD_REQUEST);
		}else {
			Optional<Category> result = categoryRepository.findByCategoryName(category.getCategoryName());
			if(!result.isEmpty()) {
				return new ResponseEntity<>(new MyExceptionDetails("Category name is already listed !",HttpStatus.BAD_REQUEST.toString()), HttpStatus.BAD_REQUEST);
			}
		}
				
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
	public ResponseEntity<List<Category>> getAllCategories(){
		return ResponseEntity.ok(categoryRepository.getAllCategories());
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
