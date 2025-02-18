package com.healthinsurence.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity

public class PRPModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String houseNo;
	private String street;
	private String city;
	private String state;
	private String pincode;
	private String customerId;
	
	
	public long getId() {
	return id;
	}
	public void setId(long id) {
	this.id = id;
	}
	public String getHouseNo() {
	return houseNo;
	}
	public void setHouseNo(String houseNo) {
	this.houseNo = houseNo;
	}
	public String getStreet() {
	return street;
	}
	public void setStreet(String street) {
	this.street = street;
	}
	public String getCity() {
	return city;
	}
	public void setCity(String city) {
	this.city = city;
	}
	public String getState() {
	return state;
	}
	public void setState(String state) {
	this.state = state;
	}
	public String getPincode() {
	return pincode;
	}
	public void setPincode(String pincode) {
	this.pincode = pincode;
	}

	public String getCustomerId() {
	return customerId;
	}
	public void setCustomerId(String customerId) {
	this.customerId = customerId;
	}
	public PRPModel(String houseNo, String street, String city, String state, String pincode, String customerId,long id) {
	super();
	this.houseNo = houseNo;
	this.id=id;
	this.street = street;
	this.city = city;
	this.state = state;
	this.pincode = pincode;
	this.customerId = customerId;
	}
	public PRPModel() {
	super();
	// TODO Auto-generated constructor stub
	}

}
