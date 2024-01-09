package com.spring.rest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest.model.UserAddress;

public interface UserAddressRepository extends JpaRepository<UserAddress, UUID> {

}
