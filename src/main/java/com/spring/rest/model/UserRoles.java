package com.spring.rest.model;

import java.util.Set;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class UserRoles {

	@Id
	@GeneratedValue
	private UUID roleId;
	
	@Column(unique = true, nullable = false)
	private String roleName;
	
	@ManyToMany(mappedBy = "roles")
	@JsonIgnoreProperties("roles")
	private Set<CustomUser> users;
	
}
