package com.spring.rest.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, UUID> {

	Optional<Roles> findByRoleName(String roleName);
}
