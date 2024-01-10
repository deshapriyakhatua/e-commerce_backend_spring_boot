package com.spring.rest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {}

