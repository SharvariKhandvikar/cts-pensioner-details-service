package com.cognizant.pensionerdetailsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.pensionerdetailsservice.model.PensionerDetails;

public interface PensionerDetailsRepository extends JpaRepository<PensionerDetails, String> {

}
