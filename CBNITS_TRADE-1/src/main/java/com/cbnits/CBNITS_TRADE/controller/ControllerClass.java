package com.cbnits.CBNITS_TRADE.controller;
//import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.UUID;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.validation.BindException;

import com.cbnits.CBNITS_TRADE.SecurityJwt.Models.AuthRequest;
import com.cbnits.CBNITS_TRADE.SecurityJwt.Models.AuthResponse;
//import com.cbnits.CBNITS_TRADE.EntityPackage.EntityClass;
//import com.example.demo.ServicePackage.ServiceInterface;
import com.cbnits.CBNITS_TRADE.ServicePackage.ServiceInterface;
import com.cbnits.CBNITS_TRADE.UsersPackage.UserLogin;
import com.cbnits.CBNITS_TRADE.UsersPackage.Users;
import com.cbnits.CBNITS_TRADE.user_passwordpackage.user_password;
import com.cbnits.CBNITS_TRADE.MyUserDetails.MyUserDetailsService;
import com.cbnits.CBNITS_TRADE.JwtUtils.JwtUtil;


@RestController
//@RequestMapping(value = "/users")

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
	@PostMapping("/user_password")
	public Map<String,String>input(@ModelAttribute user_password data, Users u)
	{  
		Map<String,String>m=new HashMap<>();
	
		UUID id=data.getId();
		UUID user_id=data.getUser_id();

		String pass=data.getPassword();
		String hash_pass=serv.getMd5(pass);
		byte[] salt=serv.getNextSalt();
//		String act_dir=data.getActivedirectoryname();
//		int auth=data.getAuthrole();
//		String email=data.getEmail();
//		String fname=data.getFirstname();
//		String lname=data.getLastname();
//		String region=data.getRegion();
		
//		serv.update2(user_id,pass,hash_pass,String.valueOf(salt));
		
		m.put("password",pass);
		m.put("hash_password",hash_pass);
		m.put("salt_pass",String.valueOf(salt));
//		
//	//	m.put("authrole",auth);
////		m.put("email",email);
////		m.put("firstname",fname);
////		m.put("lastname",lname);
////		m.put("region",region);
//		System.out.println("id="+id);
		return m;
//		
	}
	


	
	@PostMapping("/details")
	public ResponseEntity<Object> createuser(@Valid @ModelAttribute Users data, BindingResult result) throws MethodArgumentNotValidException
	{
//		
		 if (result.hasErrors()) {
        throw new MethodArgumentNotValidException(null, result);
//        return true;
         } 

		 else {
//		UUID sales = datapass.getId();
//		data.setSales_org(sales);
		String fname=data.getFirst_name();
		String lname=data.getLast_name();
		String email_id=data.getEmail_id();
		String region=data.getRegion();
		UUID salesorg=data.getSales_org();
		int authrole=data.getAuthorisation_role();
		String pass = data.getPassword();
		String hash_pass=serv.getMd5(pass);
		byte[] salt=serv.getNextSalt();
		String s = String.valueOf(salt);
		String active_directory = data.getActive_directory();
		 serv.insert(salesorg,fname,lname,email_id,region,active_directory ,authrole,hash_pass,s );
//		data.setId(id);
//		Map<String,String>m=new HashMap<>();
//		m.put("active_directory", act_dir);
//		return id;
		 
		 Map<String,Object> body = new HashMap<>();
		 body.put("error", false);
		
		 return new ResponseEntity<>(body ,HttpStatus.OK);
		 }
		
//		 return ResponseEntity.ok("User data is valid");
		 
		
	}
	
	@PostMapping("/auth")
	public Map <String,Object> get(@ModelAttribute UserLogin login) {
		UUID user_id = login .getUser_id();
		UUID sales_orgs = login.getSales_orgs();
		String password = login.getPassword();
		Map<String, Object> map = new HashMap<String, Object>();
		map = serv.fetch(user_id,password , sales_orgs);
		
		return map;
	}
	
	
	@Autowired
	private AuthenticationManager authenticationManager;

	 @Autowired
	    private MyUserDetailsService userDetailsService;

	    @Autowired
	    private JwtUtil jwtUtil;
	
	@RequestMapping(value = "/token" , method = RequestMethod.POST)
	public ResponseEntity<?> getToken(@ModelAttribute AuthRequest req) throws Exception {
	
		
		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
			);
		}  
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

  		
		final UserDetails userDetails = this.userDetailsService.loadUserByUsername(req.getUsername()) ;
		final String jwt = this.jwtUtil.generateToken(userDetails);
		System.out.println("JWT: " + jwt);

		return ResponseEntity.ok(new AuthResponse(jwt));
	}
	
	@GetMapping("/fetchuser")
	public ResponseEntity<?> fetchuser() {
		List<Users> l = new ArrayList();
		l = serv.userList();
		return ResponseEntity.ok(l);
	}
	
}
	



	














