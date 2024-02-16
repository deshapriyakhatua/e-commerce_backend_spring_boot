package com.spring.rest.projections;

import java.util.Set;

public interface CustomProduct {

	String getTitle();
	Set<CustomImage> getImages();
	
	public interface CustomImage {
		 
		String getUrl();
	}
	
}
