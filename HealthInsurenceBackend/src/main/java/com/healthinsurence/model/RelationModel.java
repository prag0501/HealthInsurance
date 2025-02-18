package com.healthinsurence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RelationModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	private String relationName;
	private String ageOfTheRelation;
	private String customerId;
	private String relationPersonName;
	private String disease;
	private String gender;
	private String diseaseText; 
	private String paymentId;
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getRelationName() {
		return relationName;
	}
	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}
	public String getAgeOfTheRelation() {
		return ageOfTheRelation;
	}
	public void setAgeOfTheRelation(String ageOfTheRelation) {
		this.ageOfTheRelation = ageOfTheRelation;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getRelationPersonName() {
		return relationPersonName;
	}
	public void setRelationPersonName(String relationPersonName) {
		this.relationPersonName = relationPersonName;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDiseaseText() {
		return diseaseText;
	}
	public void setDiseaseText(String diseaseText) {
		this.diseaseText = diseaseText;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public RelationModel(long id, String relationName, String ageOfTheRelation, String customerId,
			String relationPersonName, String disease, String gender, String diseaseText, String paymentId) {
		super();
		Id = id;
		this.relationName = relationName;
		this.ageOfTheRelation = ageOfTheRelation;
		this.customerId = customerId;
		this.relationPersonName = relationPersonName;
		this.disease = disease;
		this.gender = gender;
		this.diseaseText = diseaseText;
		this.paymentId = paymentId;
	}
	public RelationModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setFullName(String fullName) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}