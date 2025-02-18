package com.healthinsurence.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthinsurence.dto.PRPDto;
import com.healthinsurence.service.PRPService;



@RestController
@RequestMapping("/policy")
@CrossOrigin(origins="*") 
public class PRPController {

@Autowired
private PRPService prpService;


@PostMapping("/id")
public ResponseEntity<?> saveRelation(@RequestBody PRPDto prpDto) {
return prpService.savePRP(prpDto); 
}

@GetMapping("/customerdetails/{customerId}")
public ResponseEntity<?> getUserDetailsByCustomerId(@PathVariable String customerId) {
return prpService.getUserDetailsByCustomerId(customerId);
}



@GetMapping("/customerdetails")
public ResponseEntity<?> allCustomerDetails() {
return prpService.allCustomerDetails(); // Call the service to get all customers
}

//@PutMapping("/address/{customerId}")
//public ResponseEntity<String> updateAddress(@PathVariable String customerId, @RequestBody PRPDto prpDto) {
//    ResponseEntity<?> isUpdated = prpService.updatePrpDto(customerId, prpDto);
//    if (isUpdated != null) {
//        return ResponseEntity.ok("Address updated successfully.");
//    } else {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
//    }


@PutMapping("address/{customerId}")
public ResponseEntity<?> updateAddress(@PathVariable String customerId, @RequestBody PRPDto prpDto) {
    return prpService.updatePrpDto(customerId, prpDto);
}

}


