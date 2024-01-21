package com.spring.rest.model;

import java.util.Set;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue
	private UUID categoryId;
	
	@Column(nullable = false, unique = true)
	private String categoryName;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="parent_id", nullable=true)
	private Category parentId;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("category")
	private Set<Product> products;
}
