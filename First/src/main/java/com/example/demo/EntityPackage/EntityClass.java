package com.example.demo.EntityPackage;

public class EntityClass {
	private String user_id;
	private char[] pass;

	public EntityClass(String user_id,char[] pass) {
		super();
		this.user_id = user_id;
		this.pass = pass;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public char[] getPass() {
		return pass;
	}

	public void setPass(char[] pass) {
		this.pass = pass;
	}

	public EntityClass() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
