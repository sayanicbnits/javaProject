//package com.cbnits.CBNITS_TRADE.UsersPackage;
//
//import java.util.UUID;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
////@Entity
////@Table(name="users" )
////uniqueConstraints=
////@UniqueConstraint(columnNames={"region", "active_directory"})
////)
//public class UserRepo {
//	
////	@Id
////	@Column(name="id")
//	public UUID id;
//	
////	@Column(name="first_name")
//	public String first_name;
//	
////	@Column(name="last_name")
//	public String last_name;
//	
////	@Column(name="region")
//	public String region;
//	
////	@Column(name="active_directory")
//	public String active_directory;
//	
////	@Column(name="email_id")
//	public String email_id;
//	
////	@Column(name="authorisation_role", nullable = false)
//	public int authorisation_role;
//	
////	@Column(name="sales_organisation")
//	public UUID sales_org;
//	
////	@Column(name="password")
//	public String password;
//	
//	
////	public UUID getId() {
////		return id;
////	}
////	public void setId(UUID id) {
////		this.id = id;
////	}
////	public String getFirst_name() {
////		return first_name;
////	}
////	public void setFirst_name(String first_name) {
////		this.first_name = first_name;
////	}
////	public String getLast_name() {
////		return last_name;
////	}
////	public void setLast_name(String last_name) {
////		this.last_name = last_name;
////	}
////	public String getRegion() {
////		return region;
////	}
////	public void setRegion(String region) {
////		this.region = region;
////	}
////	public String getActive_directory() {
////		return active_directory;
////	}
////	public void setActive_directory(String active_directory) {
////		this.active_directory = active_directory;
////	}
////	public String getEmail_id() {
////		return email_id;
////	}
////	public void setEmail_id(String email_id) {
////		this.email_id = email_id;
////	}
////	public int getAuthorisation_role() {
////		return authorisation_role;
////	}
////	public void setAuthorisation_role(int authorisation_role) {
////		this.authorisation_role = authorisation_role;
////	}
////	public UUID getSales_org() {
////		return sales_org;
////	}
////	public void setSales_org(UUID sales_org) {
////		this.sales_org = sales_org;
////	}
////	public String getPassword() {
////		return password;
////	}
////	public void setPassword(String password) {
////		this.password = password;
////	}
////	public UserRepo(UUID id, String first_name, String last_name, String region, String active_directory,
////			String email_id, int authorisation_role, UUID sales_org, String password) {
////		super();
////		this.id = id;
////		this.first_name = first_name;
////		this.last_name = last_name;
////		this.region = region;
////		this.active_directory = active_directory;
////		this.email_id = email_id;
////		this.authorisation_role = authorisation_role;
////		this.sales_org = sales_org;
////		this.password = password;
////	}
////	public UserRepo() {
////		super();
////	}
////	
//	
//	
//}