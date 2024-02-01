package com.spring.rest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest.model.Category;
import com.spring.rest.model.Product;
import com.spring.rest.model.User;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

	List<Product> findBySeller(User user);

	List<Product> findByCategory(Category category);
	
}
