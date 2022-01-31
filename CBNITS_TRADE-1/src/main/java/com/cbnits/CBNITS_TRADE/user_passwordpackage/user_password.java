package com.cbnits.CBNITS_TRADE.user_passwordpackage;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import java.util.UUID;

import javax.persistence.Column;
//import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;
//import javax.persistence.GenericGenerator;


@Entity
@Table(name="user_password")
public class user_password {
	@Id
	@GeneratedValue(generator="UUID")
//	@GeneratedValue(strategy=GenerationType.AUTO)
	  @GenericGenerator(
		        name = "UUID",
		        strategy = "org.hibernate.id.UUIDGenerator",
		        parameters = {
		            @Parameter(
		                name = "uuid_gen_strategy_class",
		                value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
		            )
		        }
		    )
	
	
	@Column(name="id")
	private UUID id;
	
	
	@GeneratedValue(generator="UUID")
	  @GenericGenerator(
		        name = "UUID",
		        strategy = "org.hibernate.id.UUIDGenerator",
		        parameters = {
		            @Parameter(
		                name = "uuid_gen_strategy_class",
		                value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
		            )
		        }
		    )
	@Column(name="user_id")
	private UUID user_id;
	
	@Column(name="hash_password")
	private String hash_password;
	
	@Column(name="salt_password")
	private String salt_password;
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getUser_id() {
		return user_id;
	}

	public void setUser_id(UUID user_id) {
		this.user_id = user_id;
	}

	public String getHash_password() {
		return hash_password;
	}

	public void setHash_password(String hash_password) {
		this.hash_password = hash_password;
	}

	public String getSalt_password() {
		return salt_password;
	}

	public void setSalt_password(String salt_password) {
		this.salt_password = salt_password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="password")
	private String password;
	

}

