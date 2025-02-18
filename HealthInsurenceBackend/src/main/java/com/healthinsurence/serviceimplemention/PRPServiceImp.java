package com.healthinsurence.serviceimplemention;


import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.healthinsurence.dto.PRPDto;
import com.healthinsurence.model.HealthInsurenceModel;
import com.healthinsurence.model.PRPModel;
import com.healthinsurence.repo.HealthInsurenceRepository;
import com.healthinsurence.repo.PRPRepo;
import com.healthinsurence.service.PRPService;



@Service
public class PRPServiceImp implements PRPService {

@Autowired
private PRPRepo prpRepo; // PRP repository

@Autowired
private HealthInsurenceRepository healthInsurenceRepo; // Health Insurance repository

/**
* Save PRP (Personal Relation Plan) details for a customer.
*/
@Override
public ResponseEntity<?> savePRP(PRPDto prpDto) {
try {
// Check if the customer exists in HealthInsuranceModel
Optional< HealthInsurenceModel> customerData = healthInsurenceRepo.findByCustomerId(prpDto.getCustomerId());

if (customerData.isEmpty()) {
// If the customer is not found, return a 404 NOT_FOUND response
return ResponseEntity.status(HttpStatus.NOT_FOUND)
.body("Customer not found for the given ID: " + prpDto.getCustomerId());
}

// Check if an entry for the same customerId already exists in PRPModel (duplicate check)
Optional< PRPModel> existingEntry = prpRepo.findByCustomerId(prpDto.getCustomerId());

if (existingEntry.isPresent()) {
// If the entry already exists, return a 409 CONFLICT response
return ResponseEntity.status(HttpStatus.CONFLICT)
.body("Duplicate entry is not allowed for customer ID: " + prpDto.getCustomerId());
}

// Check if an entry with the same address already exists for the given customerId
Optional< PRPModel> duplicateAddress = prpRepo.findByHouseNoAndStreetAndCityAndStateAndPincode(
prpDto.getHouseNo(),
prpDto.getStreet(),
prpDto.getCity(),
prpDto.getState(),
prpDto.getPincode()
);

if (duplicateAddress.isPresent()) {
// If the address already exists, return a 409 CONFLICT response
return ResponseEntity.status(HttpStatus.CONFLICT)
.body("Duplicate address is not allowed for customer ID: " + prpDto.getCustomerId());
}

// Create and populate the PRPModel
PRPModel prpModel = new PRPModel();
prpModel.setCustomerId(prpDto.getCustomerId());
prpModel.setHouseNo(prpDto.getHouseNo());
prpModel.setStreet(prpDto.getStreet());
prpModel.setCity(prpDto.getCity());
prpModel.setState(prpDto.getState());
prpModel.setPincode(prpDto.getPincode());

// Save the new PRPModel to the repository
prpRepo.save(prpModel);

// Return a success response if the entry is saved
return ResponseEntity.status(HttpStatus.CREATED)
.body("Address details saved successfully for customer ID: " + prpDto.getCustomerId());
} catch (Exception e) {
// Catch any unexpected errors and return a 500 INTERNAL_SERVER_ERROR response
e.printStackTrace();
return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
.body("An error occurred while saving the relation.");
}
}

/**
* Retrieve user details by customer ID.
*/
@Override
public ResponseEntity<?> getUserDetailsByCustomerId(String customerId) {
try {
// Fetch the user details from the HealthInsuranceModel by customerId
Optional< PRPModel> customerData = prpRepo.findByCustomerId(customerId);

if (customerData.isPresent()) {
// Return the user details if found
return ResponseEntity.status(HttpStatus.OK).body(customerData.get());
} else {
// If the customer is not found, return a 404 NOT_FOUND response
return ResponseEntity.status(HttpStatus.NOT_FOUND)
.body("Customer not found for the given ID: " + customerId);
}
} catch (Exception e) {
// Catch any unexpected errors and return a 500 INTERNAL_SERVER_ERROR response
e.printStackTrace();
return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
.body("An error occurred while retrieving user details.");
}
}

@Override
public ResponseEntity<?> allCustomerDetails() {
try {
// Fetch all customers (all records in the PRPModel)
List< PRPModel> allCustomers = prpRepo.findAll();

// Check if no customers are found
if (allCustomers.isEmpty()) {
return ResponseEntity.status(HttpStatus.NOT_FOUND)
.body("No customers found.");
}

// Return the list of customers if found
return ResponseEntity.status(HttpStatus.OK).body(allCustomers);
} catch (Exception e) {
// Catch any unexpected errors and return a 500 INTERNAL_SERVER_ERROR response
e.printStackTrace();
return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
.body("An error occurred while retrieving all customers.");
}
}

@Override
public ResponseEntity<?> updatePrpDto(String customerId, PRPDto prpDto) {
    Optional<PRPModel> customerData = prpRepo.findByCustomerId(customerId);

    if (customerData.isPresent()) {
        PRPModel prpModel = customerData.get();
        prpModel.setHouseNo(prpDto.getHouseNo());
        prpModel.setStreet(prpDto.getStreet());
        prpModel.setCity(prpDto.getCity());
        prpModel.setState(prpDto.getState());
        prpModel.setPincode(prpDto.getPincode());

        prpRepo.save(prpModel);
        return ResponseEntity.ok("Address updated successfully for customer ID: " + customerId);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Customer not found for the given ID: " + customerId);
    }
}
}