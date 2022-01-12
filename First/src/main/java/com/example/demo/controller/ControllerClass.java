package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ServicePackage.ServiceClass;
import com.example.demo.ServicePackage.ServiceInterface;

@RestController
public class ControllerClass {
	@Autowired
	ServiceInterface serv;
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
	

@GetMapping("/hurrah")
	public String llb()
	{
 		return "Jolly LLb ban gya";
	}
	@GetMapping("/haaa")
	public String mos()
	{
 		return "Hathi mera sathi";
	}
	@GetMapping("/result")
	public int result()
	{
 		return this.serv.result();
	}
}

