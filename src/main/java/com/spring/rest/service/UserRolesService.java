package com.spring.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.rest.model.UserRoles;
import com.spring.rest.repository.UserRolesRepository;

@Service
public class UserRolesService {

	@Autowired
	private UserRolesRepository userRolesRepository;
	
	public List<UserRoles> saveRoles(Set<UserRoles> roles){
		return userRolesRepository.saveAll(roles);
	}
	
	public UserRoles saveRole(UserRoles role){
		return userRolesRepository.save(role);
	}
	
	public List<UserRoles> getRoles(){
		return userRolesRepository.findAll();
	}
	
	public UserRoles getRole(String roleName){
		Optional<UserRoles> result = userRolesRepository.findByRoleName(roleName);
		if(result.isPresent()) {
			return result.get();
		}else {
			return new UserRoles();
		}
	}
}
