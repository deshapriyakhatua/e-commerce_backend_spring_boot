package com.spring.rest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest.model.Address;

public interface AddressRepository extends JpaRepository<Address, UUID> {

}
