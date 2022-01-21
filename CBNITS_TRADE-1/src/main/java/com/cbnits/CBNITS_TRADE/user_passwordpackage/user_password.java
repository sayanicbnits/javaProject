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
	
	@Column(name="password")
	private String password;
	
	@Column(name="activedirectoryname")
	String activedirectoryname;
	
	@Column(name="authrole")
	private int authrole;
	
	@Column(name="email")
	private String email;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="region")
	private String region;

	public user_password() {
		super();
		// TODO Auto-generated constructor stub
	}

	public user_password(UUID id, String password, String activedirectoryname, int authrole, String email,
			String firstname, String lastname, String region) {
		super();
		this.id = id;
//		this.user_id = user_id;
		this.password = password;
		this.activedirectoryname = activedirectoryname;
		this.authrole = authrole;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.region = region;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActivedirectoryname() {
		return activedirectoryname;
	}

	public void setActivedirectoryname(String activedirectoryname) {
		this.activedirectoryname = activedirectoryname;
	}

	public int getAuthrole() {
		return authrole;
	}

	public void setAuthrole(int authrole) {
		this.authrole = authrole;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	
	

}
