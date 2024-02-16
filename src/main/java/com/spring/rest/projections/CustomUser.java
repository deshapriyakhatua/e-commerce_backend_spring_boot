package com.spring.rest.projections;

public interface CustomUser {
	
	 String getEmail();
	 String getPassword();
	 CustomRole getRole();
 
	 interface CustomRole {
		 String getRoleName();
	 }
 
}
