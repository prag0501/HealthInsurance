package com.healthinsurence.serviceimplemention;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.healthinsurence.dto.PaymentDto;
import com.healthinsurence.model.PaymentModel;
import com.healthinsurence.model.HealthInsurenceModel;
import com.healthinsurence.repo.HealthInsurenceRepository;
import com.healthinsurence.repo.PaymentRepo;
import com.healthinsurence.service.PaymentService;


     @Service
     public class PaymentServiceImplementation implements PaymentService {

     @Autowired
     private PaymentRepo paymentRepo;

     @Autowired
     private HealthInsurenceRepository healthInsurenceRepo;
     
     @Override
     public ResponseEntity< String> savePayment(PaymentDto paymentDto) {
     try {

     Optional< HealthInsurenceModel> customerData = healthInsurenceRepo.findByCustomerId(paymentDto.getCustomerId());

     if (customerData.isEmpty()) {

     return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found for the given ID: " + paymentDto.getCustomerId());
   }


     Optional< PaymentModel> existingEntry = paymentRepo.findByPaymentId(paymentDto.getPaymentId());

     if (existingEntry.isPresent()) {

      return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate entry is not allowed for customer ID: " + paymentDto.getCustomerId());
      }

// Create and populate the PaymentModel
        PaymentModel paymentModel = new PaymentModel(); // Creating the entity object

        paymentModel.setCustomerId(paymentDto.getCustomerId()); // Mapping the fields from DTO to entity
        paymentModel.setPaymentId(paymentDto.getPaymentId());
        paymentModel.setPremiumAmount(paymentDto.getPremiumAmount());
        paymentModel.setSumAssuredAmount(paymentDto.getSumAssuredAmount());
        paymentModel.setYear(paymentDto.getYear());
        paymentModel.setDiseaseAmount(paymentDto.getDiseaseAmount());
        paymentModel.setDiscountAmount(paymentDto.getDiscountAmount());
        paymentModel.setInsuranceType(paymentDto.getInsuranceType());
        paymentModel.setStartDate(LocalDate.now());
        paymentModel.setEndDate(LocalDate.now().plusYears(2).minusDays(1));


        paymentRepo.save(paymentModel);


        return ResponseEntity.status(HttpStatus.CREATED).body("Payment details saved successfully for customer ID: " + paymentDto.getCustomerId());
     } catch (Exception e) {

    	 e.printStackTrace();
    	 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while saving the payment details.");
     }
     }
     	@Override
     	public ResponseEntity<?> getAllPayments() {
     		try {

     			List< PaymentModel> payments = paymentRepo.findAll();

     			if (payments.isEmpty()) {
     				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No payments found.");
     			}

     			return ResponseEntity.status(HttpStatus.OK).body(payments);
     		} catch (Exception e) {

     			e.printStackTrace();
     			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching payments.");
     		}
     	}
     	@Override
     	public ResponseEntity<?> getPaymentsByCustomerId(String customerId) {

     		try {

     			List< PaymentModel> payments = paymentRepo.findByCustomerId(customerId);
     			if (payments.isEmpty()) {

     				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No payments found for customer ID: " + customerId);
     			}
     			return ResponseEntity.status(HttpStatus.OK).body(payments);
     		} catch (Exception e) {

     			e.printStackTrace();
     			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching payments.");
     		}
     	}
     	@Override
     	public ResponseEntity<?> getPaymentById(String paymentId) {
     		try {

     			Optional< PaymentModel> payment = paymentRepo.findByPaymentId(paymentId);

     			if (payment.isEmpty()) {

     				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No payment found for payment ID: " + paymentId);
     			}

     			return ResponseEntity.status(HttpStatus.OK).body(payment.get());
     			} catch (Exception e) {
     				e.printStackTrace();
     				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching the payment.");
     		}
     	}
     }
