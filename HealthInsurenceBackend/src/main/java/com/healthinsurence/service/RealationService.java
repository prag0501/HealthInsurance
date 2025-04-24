package com.healthinsurence.service;

import org.springframework.http.ResponseEntity;

import com.healthinsurence.dto.RelationDto;
public interface RealationService {

	   ResponseEntity<?>  saveHealthInsurence(RelationDto relation);

	ResponseEntity<?> getAllRelations();

	ResponseEntity<?> checkCustomerDetails(RelationDto relation);

	ResponseEntity<?> customerDetails(String customerId);

	ResponseEntity<?> getPaymentById(String paymentId);

	ResponseEntity<?> validateRelation(RelationDto relation);



	
	}


