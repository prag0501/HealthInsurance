package com.healthinsurence.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthinsurence.model.HealthInsurenceModel;

@Repository
public interface HealthInsurenceRepository extends JpaRepository<HealthInsurenceModel, Long>{
	Optional<HealthInsurenceModel> findByMobileNo(String mobileNo);
    Optional<HealthInsurenceModel> findByEmail(String email);
	Optional<HealthInsurenceModel> findByMobileNoOrEmail(String mobileNo, String email);
	Optional<HealthInsurenceModel> findByCustomerId(String customerId);
}
