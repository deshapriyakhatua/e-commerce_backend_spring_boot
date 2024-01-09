package com.spring.rest.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest.model.UserRoles;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, UUID> {

	Optional<UserRoles> findByRoleName(String roleName);
}
