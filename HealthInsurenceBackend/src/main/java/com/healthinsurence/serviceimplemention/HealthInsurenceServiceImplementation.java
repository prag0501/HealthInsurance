package com.healthinsurence.serviceimplemention;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.healthinsurence.dto.HealthInsurenceDto;
import com.healthinsurence.model.HealthInsurenceModel;
import com.healthinsurence.otp.OtpService;
import com.healthinsurence.repo.HealthInsurenceRepository;
import com.healthinsurence.service.HealthInsurenceService;
@Service
public class HealthInsurenceServiceImplementation implements HealthInsurenceService{
	
	
	@Autowired
	private HealthInsurenceRepository repository;
	
    @Autowired
    private OtpService otpservice;
	
    @Override
    public ResponseEntity<?> saveHealthInsurence(HealthInsurenceDto dto) {
    
    Optional<HealthInsurenceModel> data = ((HealthInsurenceRepository) repository).findByMobileNoOrEmail(dto.getMobileNo(),dto.getEmail());
    
    if (data.isEmpty()) {
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddmmss");
        String customerId = now.format(formatter);

        HealthInsurenceModel insuranceModel = new HealthInsurenceModel();
        insuranceModel.setFullName(dto.getFullName());
        insuranceModel.setMobileNo(dto.getMobileNo());
        insuranceModel.setEmail(dto.getEmail());
        insuranceModel.setGender(dto.getGender());
        insuranceModel.setCustomerId(customerId);
        repository.save(insuranceModel);
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Data is stored successfully");
        
    } else {
    	
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Data already exists for the provided mobile number or email " );
    }
}
	 @Override
	    public ResponseEntity<?> findByMobileNo(String mobileNo) {
	        Optional<HealthInsurenceModel> data = ((HealthInsurenceRepository) repository).findByMobileNo(mobileNo);
	        if(data.isEmpty()) 
	        {
	        	return ResponseEntity.status(HttpStatus.OK).body("data Was not found with the MobileNo");
	        }
	       	else
	       	{
	       	 return ResponseEntity.status(HttpStatus.OK).body("found");
	        
	        }
	        
	        }
	 
	    @Override
	    public ResponseEntity<?> fetchByMobileNo(String mobileNo) {
	        Optional<HealthInsurenceModel> data = repository.findByMobileNo(mobileNo);
	        if (data.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("Data was not found with the MobileNo: " + mobileNo);
	        } else {
	            return ResponseEntity.status(HttpStatus.OK).body(data.get());
	        }
	    }
	 
	 @Override
	 public ResponseEntity<?> findByEmail(String email) {
	        Optional<HealthInsurenceModel> data1 = ((HealthInsurenceRepository) repository).findByEmail(email);
	        if(data1.isEmpty()) 
	        {
	        	return ResponseEntity.status(HttpStatus.OK).body("data Was not found with the Email");
	        }
	       	else
	       	{
	       	 return ResponseEntity.status(HttpStatus.OK).body("Found");
	        
	        }
	        
	        }
	 
	 @Override
	    public ResponseEntity<?> generateOtp() {
	        String otp = otpservice.generateOtp(); 
	        return ResponseEntity.status(HttpStatus.OK).body("OTP sent: " + otp);
	    }
	 
	 @Override 
	    public List<HealthInsurenceModel> getAllUsers() {
	        return repository.findAll();
	    }
	 
	 
	}