package com.healthinsurence.repo;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthinsurence.model.PaymentModel;

@Repository
public interface PaymentRepo extends JpaRepository<PaymentModel, Long> {
	
List< PaymentModel> findByCustomerId(String customerId);

Optional< PaymentModel> findByPaymentId(String paymentId);

}