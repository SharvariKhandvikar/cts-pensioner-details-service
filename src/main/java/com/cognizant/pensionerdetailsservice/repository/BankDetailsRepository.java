package com.cognizant.pensionerdetailsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.pensionerdetailsservice.model.BankDetails;

//Bank JPA repository
public interface BankDetailsRepository extends JpaRepository<BankDetails, String> {

}
