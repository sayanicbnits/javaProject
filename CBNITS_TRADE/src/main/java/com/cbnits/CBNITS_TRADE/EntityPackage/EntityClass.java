package com.cbnits.CBNITS_TRADE.EntityPackage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;

@Entity
@Table(name="user_password")
public class EntityClass {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	@Column(name="user_id")
	private String user_id;
	@Column(name="password")
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
