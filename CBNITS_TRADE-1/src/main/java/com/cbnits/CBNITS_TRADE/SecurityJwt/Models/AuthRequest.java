package com.cbnits.CBNITS_TRADE.SecurityJwt.Models;

import java.io.Serializable;

public class AuthRequest  implements Serializable{
	private String username;
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

    //need default constructor for JSON Parsing
    public AuthRequest()
    {

    }
// String password
    public AuthRequest(String username) {
        this.setUsername(username);
        this.setPassword(password);
    }
    
    
    
//	private String  username;
//	private String  first_name;
//	private String  last_name;
//	private String  auth_roll;
//	public AuthRequest(String username, String first_name, String last_name, String auth_roll) {
//		super();
//		this.username = username;
//		this.first_name = first_name;
//		this.last_name = last_name;
//		this.auth_roll = auth_roll;
//	}
//	
//	public AuthRequest() {
//		super();
//	}
//	
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getFirst_name() {
//		return first_name;
//	}
//	public void setFirst_name(String first_name) {
//		this.first_name = first_name;
//	}
//	
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
	
	
	
}
