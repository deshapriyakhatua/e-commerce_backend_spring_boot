package com.spring.rest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spring.rest.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {

}
