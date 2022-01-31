package com.cbnits.CBNITS_TRADE.UsersPackage;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sales_organisation")

public class Salesorg {
	@Id
	@GeneratedValue(generator="UUID")
	@Column(name="id")
	private UUID id;
	
	@Column(name="country")
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String country;
	@Column(name="currency")
	private String currency;
	@Column(name="plants")
	private int plants;
	@Column(name="bergu")
	private String bergu;
	@Column(name="sales_organisation")
	private String sales_organisation;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public int getPlants() {
		return plants;
	}
	public void setPlants(int plants) {
		this.plants = plants;
	}
	public String getBergu() {
		return bergu;
	}
	public void setBergu(String bergu) {
		this.bergu = bergu;
	}
	public String getSales_organisation() {
		return sales_organisation;
	}
	public void setSales_organisation(String sales_organisation) {
		this.sales_organisation = sales_organisation;
	}
	
}