package com.healthinsurence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.healthinsurence.dto.PaymentDto;
import com.healthinsurence.service.PaymentService;
	

	@RestController
	@RequestMapping("/payment")
	@CrossOrigin(origins = "*")
	public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/add")
	public ResponseEntity<?> addPayment(@RequestBody PaymentDto paymentDto) {
	return paymentService.savePayment(paymentDto);
	}


	@GetMapping("/all")
	public ResponseEntity<?> getAllPayments() {
	System.out.println("Fetching all payments...");
	return paymentService.getAllPayments();
	}


	@GetMapping("/customerid")
	public ResponseEntity<?> getPaymentsByCustomerId(@RequestParam String customerId) {
	return paymentService.getPaymentsByCustomerId(customerId);
	}


	@GetMapping("/paymentid")
	public ResponseEntity<?> getPaymentById(@RequestParam String paymentId) {
	return paymentService.getPaymentById(paymentId);
	}
	}

	