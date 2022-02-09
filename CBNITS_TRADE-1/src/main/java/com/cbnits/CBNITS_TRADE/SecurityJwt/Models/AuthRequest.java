package com.cbnits.CBNITS_TRADE.SecurityJwt.Models;

import java.io.Serializable;

public class AuthRequest  implements Serializable{
	private String username;
//    private String lastname;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
   

//    public String getLastname() {
//		return lastname;
//	}
//
//	public void setLastname(String lastname) {
//		this.lastname = lastname;
//	}

	
	

	public AuthRequest(String username, String password) {
		super();
		this.username = username;
//		this.lastname = lastname;
		this.password = password;
		
	}

	//need default constructor for JSON Parsing
    public AuthRequest()
    {

    }

    
    
    
//	private String  username;
//	private String  firstname;
//	private String  last_name;
//	private String  auth_roll;
//	private String password;
//	public AuthRequest( String firstname, String password) {
//		super();
//		this.username = username;
//		this.firstname = firstname;
//		this.last_name = last_name;
//		this.auth_roll = auth_roll;
//		this.password = password;
//	}
	
//	public AuthRequest() {
//		super();
//	}
	
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}

//	public String getFirstname() {
//		return firstname;
//	}
//	public void setFirst_name(String firstname) {
//		this.firstname = firstname;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
	
	
//	public String getLast_name() {
//		return last_name;
//	}
//	
//
//	public void setLast_name(String last_name) {
//		this.last_name = last_name;
//	}
//	
//	public String getAuth_roll() {
//		return auth_roll;
//	}
//	public void setAuth_roll(String auth_roll) {
//		this.auth_roll = auth_roll;
//	}
//	
	
	
}
