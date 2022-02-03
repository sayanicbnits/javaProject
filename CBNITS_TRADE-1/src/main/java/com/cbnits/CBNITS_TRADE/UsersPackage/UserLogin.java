package com.cbnits.CBNITS_TRADE.UsersPackage;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_login" )

public class UserLogin {

	@Id
	@GeneratedValue(generator="UUID")
	@Column(name="id")
	private UUID id;
	
	@Column(name="user_id")
	private UUID user_id;
	
	@Column(name="password")
	private String password;
	
	@Column(name="sales_orgs")
	private UUID sales_orgs;
	
	
	public UUID getUser_id() {
		return user_id;
	}
	public void setUser_id(UUID user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UUID getSales_orgs() {
		return sales_orgs;
	}
	public void setSales_orgs(UUID sales_orgs) {
		this.sales_orgs = sales_orgs;
	}
	

}
