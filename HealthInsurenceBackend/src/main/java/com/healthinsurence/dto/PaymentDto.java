package com.healthinsurence.dto;

public class PaymentDto {

	 private Long Id;
	    private String customerId;
	    private String paymentId;
	    private String premeiumAmount;
	    private String sumAssuredAmount;
	    private String year;
	    private String diseaseAmount;
	    private String discountAmount;
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
		public String getPremeiumAmount() {
			return premeiumAmount;
		}
		public void setPremeiumAmount(String premeiumAmount) {
			this.premeiumAmount = premeiumAmount;
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
		
	    

	}

