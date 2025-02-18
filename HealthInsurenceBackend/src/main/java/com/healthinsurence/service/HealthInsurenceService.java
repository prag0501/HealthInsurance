package com.healthinsurence.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.healthinsurence.dto.HealthInsurenceDto;
import com.healthinsurence.model.HealthInsurenceModel;

public interface HealthInsurenceService {

	

	ResponseEntity<?>  saveHealthInsurence(HealthInsurenceDto health);
	ResponseEntity<?> findByEmail(String email);
	ResponseEntity<?> generateOtp();
	ResponseEntity<?> fetchByMobileNo(String mobileNo);
	ResponseEntity<?> findByMobileNo(String mobileNo);
	List<HealthInsurenceModel> getAllUsers();
	ResponseEntity<?> updateMobileNumber(String customerId, String mobileNo);
	ResponseEntity<?> updateEmail(String customerId, String email);
	ResponseEntity<?> updateFullName(String customerId, String fullName);
	

}