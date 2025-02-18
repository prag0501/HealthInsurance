package com.healthinsurence.dto;

import java.time.LocalDate;

public class PaymentDto {

	private Long Id;
	private String customerId;
	private String paymentId;
	private String premiumAmount;
	private String sumAssuredAmount;
	private String year;
	private String diseaseAmount;
	private String discountAmount;
	private String insuranceType;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getPremiumAmount() {
		return premiumAmount;
	}
	public void setPremiumAmount(String premiumAmount) {
		this.premiumAmount = premiumAmount;
	}
	public String getSumAssuredAmount() {
		return sumAssuredAmount;
	}
	public void setSumAssuredAmount(String sumAssuredAmount) {
		this.sumAssuredAmount = sumAssuredAmount;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getDiseaseAmount() {
		return diseaseAmount;
	}
	public void setDiseaseAmount(String diseaseAmount) {
		this.diseaseAmount = diseaseAmount;
	}
	public String getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}
	public String getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public PaymentDto(Long id, String customerId, String paymentId, String premiumAmount, String sumAssuredAmount,
			String year, String diseaseAmount, String discountAmount, String insuranceType, LocalDate startDate,
			LocalDate endDate) {
		super();
		Id = id;
		this.customerId = customerId;
		this.paymentId = paymentId;
		this.premiumAmount = premiumAmount;
		this.sumAssuredAmount = sumAssuredAmount;
		this.year = year;
		this.diseaseAmount = diseaseAmount;
		this.discountAmount = discountAmount;
		this.insuranceType = insuranceType;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public PaymentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}