package com.healthinsurence.service;



import org.springframework.http.ResponseEntity;

import com.healthinsurence.dto.PRPDto;



public interface PRPService {



ResponseEntity<?> savePRP(PRPDto prp);



ResponseEntity<?> getUserDetailsByCustomerId(String customerId);


ResponseEntity<?> allCustomerDetails();


ResponseEntity<?> updatePrpDto(String customerId, PRPDto prpDto);

}
