package com.spring.rest.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
	Optional<Category> findByCategoryName(String categoryName);
}
