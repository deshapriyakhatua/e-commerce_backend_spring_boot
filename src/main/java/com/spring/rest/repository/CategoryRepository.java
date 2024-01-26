package com.spring.rest.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.spring.rest.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
	Optional<Category> findByCategoryName(String categoryName);
	
	@Query("select new com.spring.rest.model.Category(c.categoryName) from Category c")
	public List<Category> getAllCategories();
}
