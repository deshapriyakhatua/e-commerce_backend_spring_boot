package com.spring.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest.model.Roles;
import com.spring.rest.repository.RolesRepository;

@Service
public class RolesService {

	@Autowired
	private RolesRepository userRolesRepository;
	
	public List<Roles> saveRoles(Set<Roles> roles){
		return userRolesRepository.saveAll(roles);
	}
	
	public Roles saveRole(Roles role){
		return userRolesRepository.save(role);
	}
	
	public List<Roles> getRoles(){
		return userRolesRepository.findAll();
	}
	
	public Roles getRole(String roleName){
		Optional<Roles> result = userRolesRepository.findByRoleName(roleName);
		if(result.isPresent()) {
			return result.get();
		}else {
			return new Roles();
		}
	}
}
