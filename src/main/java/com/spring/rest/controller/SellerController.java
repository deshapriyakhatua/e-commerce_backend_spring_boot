package com.spring.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.model.Category;
import com.spring.rest.service.CategoryService;

@RestController
@RequestMapping("/seller")
public class SellerController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllUsers() {
		return categoryService.getAllCategories();
	}
	
}
