package com.healthinsurence.repo;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthinsurence.model.PRPModel;



public interface PRPRepo extends JpaRepository< PRPModel, Long>
{

Optional< PRPModel> findByCustomerId(String customerId);

Optional<PRPModel> findByHouseNoAndStreetAndCityAndStateAndPincode(String houseNo, String street, String city,
		String state, String pincode);

}
