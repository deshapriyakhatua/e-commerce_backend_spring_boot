package com.spring.rest.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.spring.rest.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	
	Optional<User> findByEmail(String email);
	
	
	// custom query
	
	@Query("select new com.spring.rest.model.User(u.email, u.password, u.role) from User u join u.role r where u.email = ?1")
	Optional<User> getByEmail(String email);
	
	@Query("select new com.spring.rest.model.User(u.email, u.password, u.role) from User u")
	public List<User> getAllUsers();
	
}
