package com.cbnits.CBNITS_TRADE.UsersPackage;

//import java.beans.JavaBean;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class Users {
	@Id
	@GeneratedValue(generator="UUID")
	@Column(name="id")
	private UUID id;
	
	@Column(name="active_directory")
	private String activedir;

	@Column(name="first_name")
	private String first_name;
	
	@Column(name="last_name")
	private String last_name;
	
	@Column(name="region")
	private String region;
	
	@Column(name="email_id")
	private String emailid;
	
	@Column(name="authorisation_role")
	private int authorisation_role;
	
	@Column(name="sales_organisation")
	private UUID sales_org;
	
	public UUID getSales_org() {
		return sales_org;
	}

	public void setSales_org(UUID sales_org) {
		this.sales_org = sales_org;
	}

	public Users(UUID id, String activedir, String first_name, String last_name, String region, String emailid,
			int authorisation_role, UUID sales_org) {
		super();
		this.id = id;
		this.activedir = activedir;
		this.first_name = first_name;
		this.last_name = last_name;
		this.region = region;
		this.emailid = emailid;
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

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public int getAuthorisation_role() {
		return authorisation_role;
	}

	public void setAuthorisation_role(int authorisation_role) {
		this.authorisation_role = authorisation_role;
	}

	public Users(UUID id, String activedir, String first_name, String last_name, String region, String emailid,
			int authorisation_role) {
		super();
		this.id = id;
		this.activedir = activedir;
		this.first_name = first_name;
		this.last_name = last_name;
		this.region = region;
		this.emailid = emailid;
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

	public String getActivedir() {
		return activedir;
	}

	public void setActivedir(String activedir) {
		this.activedir = activedir;
	}
	
	
}
