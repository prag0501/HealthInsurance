package com.healthinsurence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthinsurence.model.RelationModel;

@Repository
public interface RelationRepository extends JpaRepository<RelationModel, Long> {


	List<RelationModel> findByCustomerId(String customerId);
//	Optional<RelationModel> findByPaymentId(String paymentId);
	List<RelationModel> findByPaymentId(String paymentId);
		
}
