package com.ecommerce.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/demo")
public class Demo {

	@GetMapping
	public String getMessage() {
		return "Im Live From demo class";
	}
}
