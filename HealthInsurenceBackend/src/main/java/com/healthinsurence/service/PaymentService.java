package com.healthinsurence.service;
	
	import org.springframework.http.ResponseEntity;

	import com.healthinsurence.dto.PaymentDto;

	public interface PaymentService {

	ResponseEntity< String> savePayment(PaymentDto paymentDto);

	ResponseEntity<?> getAllPayments();

	ResponseEntity<?> getPaymentsByCustomerId(String customerId);

	ResponseEntity<?> getPaymentById(String paymentId);


	}