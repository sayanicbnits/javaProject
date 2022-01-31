package com.cbnits.CBNITS_TRADE.UsersPackage;

//import java.beans.JavaBean;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
//import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.sun.istack.NotNull;

@Entity
@Table(name="users" ,
uniqueConstraints=
@UniqueConstraint(columnNames={"region", "active_directory"})
)
public class Users {
	@Id
	@GeneratedValue(generator="UUID")
	@Column(name="id")
	private UUID id;
	
	
	@NotEmpty(message = "first name must not be empty")
	@Size(min = 2, max = 50, message = "The length of full name must be between 2 and 50 characters.")
	@Column(name="first_name")
	private String first_name;
	
	@NotEmpty(message = "last name must not be empty")
	@Size(min = 2, max = 50, message = "The length of last name must be between 2 and 50 characters.")
	@Column(name="last_name")
	private String last_name;
	
	@NotEmpty(message = "Region is mandatory")
	@Column(name="region")
	private String region;
	
	@NotEmpty(message = "Active_directory must not be empty")
	@Column(name="active_directory")
	private String active_directory;
		
	
	@Pattern(regexp = "[a-z0-9]+@gmail.com" ,message = "email should be a valid email")
	@NotEmpty(message = "email must not be empty")
//    @Email(message = "email should be a valid email")
	@Column(name="email_id")
	private String email_id;
	
	
//	@NotNull(message= "salary may not be empty") 
	@Range(min = 1, max = 2, message= "authorisation_role must not be empty or null")
	@Column(name="authorisation_role", nullable = false)
	private int authorisation_role;
	
	@Column(name="sales_organisation")
	private UUID sales_org;
	
//    @ValidPassword
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",message="Password should contain atleast 8 characters , 1 Uppercase, 1 Lowercase, 1 special character , 1 Numeric character" )
	@NotEmpty(message = "Password must not be empty")
//	@Range(min = 8,max = 20 )
	@Column(name="password")
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UUID getSales_org() {
		return sales_org;
	}

	public void setSales_org(UUID sales_org) {
		this.sales_org = sales_org;
	}

	public Users(UUID id, String active_directory, String first_name, String last_name, String region, String email_id,
			int authorisation_role, UUID sales_org) {
		super();
		this.id = id;
		this.active_directory = active_directory;
		this.first_name = first_name;
		this.last_name = last_name;
		this.region = region;
		this.email_id = email_id;
		this.authorisation_role = authorisation_role;
		this.sales_org = sales_org;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public int getAuthorisation_role() {
		return authorisation_role;
	}

	public void setAuthorisation_role(int authorisation_role) {
		this.authorisation_role = authorisation_role;
	}

	public Users(UUID id, String active_directory, String first_name, String last_name, String region, String email_id,
			int authorisation_role) {
		super();
		this.id = id;
		this.active_directory = active_directory;
		this.first_name = first_name;
		this.last_name = last_name;
		this.region = region;
		this.email_id = email_id;
		this.authorisation_role = authorisation_role;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*public Users(UUID id, String activedir) {
		super();
		this.id = id;
		this.activedir = activedir;
	}
*/
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getActive_directory() {
		return active_directory;
	}

	public void setActive_directory(String active_directory) {
		this.active_directory = active_directory;
	}

	
	
	
}
