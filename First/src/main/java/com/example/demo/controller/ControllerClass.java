package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerClass {
	@GetMapping("/hlw")
	public String hello()
	{
		return "hello world";
	}
	
	@PostMapping("/hlw")
	public String hello1()
	{
		return "cbnits";
	}

}
