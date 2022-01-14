package com.example.demo.controller;

import java.util.Scanner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EntityPackage.EntityClass;
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
 		return "hello Everyone from cbnits family";
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


	@GetMapping("/hey")
	public String hey()
	{
		return "Hey" ;
    }
	@GetMapping("/result")
	public int result()
	{
 		return this.serv.result();

	}
	@GetMapping("/Carry")
	public String Minati()
	{
 		return "Ajey Nagar is India's biggest youtuber";
	}
	@GetMapping("/GeneratePassword")
	public String new1(@ModelAttribute EntityClass data) 
	{
		//Scanner sc=new Scanner(System.in);
		//System.out.println("enter password");
		//char pass[]=sc.next().toCharArray();
	//	CallingMethod ab=new CallingMethod();
		//return(FirstApplication.md5(String.valueOf(ab.call())))
		byte salt[]=serv.getNextSalt();
		byte saltedPass[]=serv.hash(data.getPass(),salt);
		return this.serv.md5(String.valueOf(saltedPass));

	}
	
}


