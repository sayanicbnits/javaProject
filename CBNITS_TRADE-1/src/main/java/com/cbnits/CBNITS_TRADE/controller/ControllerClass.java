package com.cbnits.CBNITS_TRADE.controller;
//import java.nio.charset.StandardCharsets;

import java.util.HashMap;

import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbnits.CBNITS_TRADE.*;
//import com.cbnits.CBNITS_TRADE.EntityPackage.EntityClass;
//import com.example.demo.ServicePackage.ServiceInterface;
import com.cbnits.CBNITS_TRADE.ServicePackage.ServiceInterface;
import com.cbnits.CBNITS_TRADE.UsersPackage.Users;
import com.cbnits.CBNITS_TRADE.user_passwordpackage.user_password;

@RestController

public class ControllerClass {
	@Autowired
	ServiceInterface serv;
	@GetMapping("/hlw")
	public String hello()
	{
		return "hello everyone from CBNITS";
	}
	@PostMapping("/add")
	public int hello1()
	{
		int a=10;
		int b=6;
		a=a+b;	
		return a;
	}
	/*
	@GetMapping("/GeneratePassword")
	public Map<String,String> new1(@ModelAttribute EntityClass data) 
	{
		Map<String,String> m = new HashMap<String,String>();
		//Scanner sc=new Scanner(System.in);
		//System.out.println("enter password");
		//char pass[]=sc.next().toCharArray();
	//	CallingMethod ab=new CallingMethod();
		//return(FirstApplication.md5(String.valueOf(ab.call())))
		String user_id = data.getUser_id();
		String password=data.getPass();
		char passwordarr[] = password.toCharArray();
		byte salt[]=serv.getNextSalt();
		String s=new String(salt);
	//	byte saltedPass[]=serv.hash(data.getPass(),salt);
	//	 String pass = this.serv.md5(String.valueOf(saltedPass));
		byte hashedpass[]=serv.hash(passwordarr, salt);
		String hashedpassstr=new String(hashedpass);
		 serv.update1(user_id,hashedpass,salt);
		 m.put("user_id", user_id);
		 m.put("password",hashedpassstr);
		 return m;
	}
	@GetMapping("/GeneratePassword1")
	public Map<String,String>genpass(@ModelAttribute EntityClass data)
	{
		Map<String,String>m=new HashMap<>();
		byte salt[]=serv.getNextSalt();
		System.out.println(salt);
		String pass=data.getPass();
		String user_id=data.getUser_id();
		char[] passchar=pass.toCharArray();
		byte []passbyte=pass.getBytes();
		byte[] hashedpass=serv.hash(passchar, salt);
		System.out.println(hashedpass);
		serv.update1(user_id,hashedpass, String.valueOf(salt));
		m.put("user_id", user_id);
		m.put("password",pass);
		return m;
		
		
	}*/
	/*@GetMapping("/genpass")
	public Map<String,String>genpass1(@ModelAttribute EntityClass data)
	{	
		try
		{
		Map<String,String>m=new HashMap<>();
		byte[]salt=serv.getNextSalt();
	//	String s=new String(salt,StandardCharsets.UTF_8);
		String pass=data.getPass();
			
		String user_id=data.getUser_id();
		String md5pass=serv.getMd5(pass);
	//	char md5char[]=md5pass.toCharArray();
	//	byte[] hashedbyte=serv.hash(md5char, salt);
		serv.update1(user_id,md5pass,String.valueOf(salt));
		m.put("user_id", user_id);
		m.put("password",md5pass);
		return m;
		}
		catch(Exception e)
		{
			Map<String,String>n=new HashMap<>();
			n.put("Status: ","User_id already in use");
			return n;
		}
	}
	
	@PostMapping("/login")
	public Map<String,String> authenticate(@ModelAttribute EntityClass data)
	{
		String user=data.getUser_id();
		String pass=data.getPass();
		return(serv.authenticate(user,pass));
		
	}*/
	@PostMapping("/provide_user_password_details")
	public Map<String,String>input(@ModelAttribute user_password data)
	{
		Map<String,String>m=new HashMap<>();
		UUID id=data.getId();
		UUID user_id=data.getUser_id();

		String pass=data.getPassword();
		String hash_pass=serv.getMd5(pass);
		byte[] salt=serv.getNextSalt();
		String act_dir=data.getActivedirectoryname();
		int auth=data.getAuthrole();
		String email=data.getEmail();
		String fname=data.getFirstname();
		String lname=data.getLastname();
		String region=data.getRegion();
		
		serv.update2(user_id,pass,hash_pass,String.valueOf(salt),act_dir,auth,email,fname,lname,region);
		
		m.put("password",pass);
		m.put("hash_password",hash_pass);
		m.put("salt_pass",String.valueOf(salt));
		m.put("activedirectoryname",act_dir);
	//	m.put("authrole",auth);
		m.put("email",email);
		m.put("firstname",fname);
		m.put("lastname",lname);
		m.put("region",region);
		System.out.println("id="+id);
		return m;
		
	}
	
	@PostMapping("/usersdetails")
	public UUID getuser(@ModelAttribute Users data)
	{
		String act_dir=data.getActivedir();
		String fname=data.getFirst_name();
		String lname=data.getLast_name();
		String emailid=data.getEmailid();
		String region=data.getRegion();
		UUID sales=data.getSales_org();
		int authrole=data.getAuthorisation_role();
		UUID id= serv.update3(sales,fname,lname,emailid,region,act_dir,authrole);
		Map<String,String>m=new HashMap<>();
		m.put("active_directory", act_dir);
		return id;
		
	}
	
	
}






