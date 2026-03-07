package com.ecommerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dtos.SignupDto;

@RestController
@RequestMapping("/demo")
public class Demo {

	@GetMapping
	public String getMessage(){
		
		return "Im live from Demo class";
	}
}
