package com.healthinsurence.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthinsurence.dto.PRPDto;
import com.healthinsurence.service.PRPService;

@RestController
@RequestMapping("/policy")
@CrossOrigin(origins="*") // Allows CORS from all origins
public class PRPController {

@Autowired
private PRPService prpservice; // Inject the PRPService

@PostMapping("/id") // POST endpoint to save a relation
public ResponseEntity<?> saveRelation(@RequestBody PRPDto prpDto) {
return prpservice.savePRP(prpDto); // Delegate the request to the service layer
}
}

