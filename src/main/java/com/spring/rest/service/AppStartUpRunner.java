package com.spring.rest.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.spring.rest.model.Cart;
import com.spring.rest.model.Category;
import com.spring.rest.model.Role;
import com.spring.rest.model.User;
import com.spring.rest.repository.CategoryRepository;
import com.spring.rest.repository.RoleRepository;
import com.spring.rest.repository.UserRepository;
import jakarta.transaction.Transactional;


@Component
@Transactional
public class AppStartUpRunner implements CommandLineRunner{

	@Autowired
	private RoleRepository userRolesRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
    public void run(String...args) throws Exception {
		
//		Set<Role> userRoles = new HashSet<Role>();
//		userRoles.add(new Role("ROLE_ADMIN"));
//		userRoles.add(new Role("ROLE_USER"));
//		userRoles.add(new Role("ROLE_SELLER"));
//		try {
//			userRolesRepository.saveAll(userRoles);
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		
//		User user = new User();
//		user.setEmail("admin@gmail.com");
//		user.setPassword(passwordEncoder.encode("admin"));
//		Optional<Role> optionalRole = userRolesRepository.findByRoleName("ROLE_ADMIN");
//		Role role = optionalRole.get();
//		user.setRole(role);
//		user.setCart(new Cart());
//		try {
//			userRepository.save(user);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		Set<Category> categories = new HashSet<>();
//		categories.add(new Category("t-shirt"));
//		categories.add(new Category("shirt"));
//		categories.add(new Category("jeans"));
//		try {
//			categoryRepository.saveAll(categories);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}

	
}
