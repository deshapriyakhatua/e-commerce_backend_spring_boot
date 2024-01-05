package com.spring.rest.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.rest.model.UserRoles;

public interface UserRolesRepository extends JpaRepository<UserRoles, UUID> {

	Optional<UserRoles> findByRoleName(String roleName);
}
