package com.spring.rest.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@GetMapping("/")
	public String home() {
		return "welcome to Spring Home Page";
	}
}
