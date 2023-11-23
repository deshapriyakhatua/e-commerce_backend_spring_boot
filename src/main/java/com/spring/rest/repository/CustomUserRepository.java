package com.spring.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spring.rest.model.CustomUser;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, Integer> {

	Optional<CustomUser> findByEmail(String email);
}
