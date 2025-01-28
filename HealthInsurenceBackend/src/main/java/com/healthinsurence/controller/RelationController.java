package com.healthinsurence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthinsurence.dto.RelationDto;
import com.healthinsurence.service.RealationService;

@RestController

@RequestMapping("/relation")
@CrossOrigin(origins = "*")
public class RelationController {
	@Autowired
	private RealationService service;
	private RelationDto relation;

//	@PostMapping("/relationdetails")
//	public ResponseEntity<?> post(@RequestBody RelationDto relation) {
//		return service.saveHealthInsurence(relation);
//	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllRelations() {
		return service.getAllRelations();
	}

	@PostMapping("/check-customerId")
	public ResponseEntity<?> checkCustomerDetails(@RequestBody RelationDto relation) {
		return service.checkCustomerDetails(relation);
	}
	@GetMapping("/customer-details")
	public ResponseEntity<?> customerDetails(@RequestParam String customerId) {
	   // RelationDto relation = new RelationDto();
	    //relation.setCustomerId(customerId);
	    return service.customerDetails(customerId);
	}

}
