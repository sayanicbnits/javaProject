package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerClass {
	@GetMapping("/hlw")
	public String hello()
	{
		return "hello CBNITS";
	}
	
	@PostMapping("/add")
	public int hello1()
	{
		int a=10;
		int b=6;
		a=a+b;	
		return a;
	}
	@GetMapping("/hi")
	public String HI()
	{
 		return "hello Everyone";
	}

}
