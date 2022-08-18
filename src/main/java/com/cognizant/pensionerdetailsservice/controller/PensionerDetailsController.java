package com.cognizant.pensionerdetailsservice.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.pensionerdetailsservice.model.BankDetails;
import com.cognizant.pensionerdetailsservice.model.PensionerDetails;
import com.cognizant.pensionerdetailsservice.model.UserLogin;
import com.cognizant.pensionerdetailsservice.proxy.AuthenticationProxy;
import com.cognizant.pensionerdetailsservice.repository.BankDetailsRepository;
import com.cognizant.pensionerdetailsservice.repository.PensionerDetailsRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PensionerDetailsController {
	
	Logger log = LoggerFactory.getLogger(PensionerDetailsController.class);
	
	@Autowired
	PensionerDetailsRepository repo;
	
	@Autowired
	BankDetailsRepository bankRepo;
	
	@Autowired
	AuthenticationProxy proxy;
	
	@GetMapping("/pensionerdetails/{adharNumber}")
	public PensionerDetails getPensionerDetailsByAdhar(@RequestHeader(name = "Authorization") String token,
			@PathVariable("adharNumber") String adharNumber) {
		
		if(!proxy.getValidity(token)) {
			log.info("Auth Runtime exception is throwing");
			throw new RuntimeException("invalid token " + proxy.getValidity(token));
		}
		PensionerDetails pensionerDetails = null;
		Optional<PensionerDetails> list = repo.findById(adharNumber);
		if(!list.isEmpty()) {
		pensionerDetails = list.get();}
		
		if(pensionerDetails == null) {
			log.info("pension Runtime exception is throwing");
			throw new RuntimeException("Invalid pensioner detail provided, please provide valid detail");
		}
		
		
		return pensionerDetails;
	}
	
	@PostMapping("/save/pensionerdetails")
	public PensionerDetails savePensioner(@RequestHeader(name = "Authorization") String token,
			@RequestBody PensionerDetails pensionerDetails) {
		
		if(!proxy.getValidity(token))
			throw new RuntimeException("invalid token " + proxy.getValidity(token));

		BankDetails bd  = bankRepo.save(pensionerDetails.getBankDetails());
		PensionerDetails pd = repo.save(pensionerDetails);
		
		System.out.println("The new employee is saved "+ pd.toString());
		return pd;
	}
	
	

	//To generate token for Junit testing
	public String  generateToken() {
		String token = proxy.login(new UserLogin("user1", "user1")).getToken();
		log.info("token ======>>>> "+token);
		return token;
	}

}
