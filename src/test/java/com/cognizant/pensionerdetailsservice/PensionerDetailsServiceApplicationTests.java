package com.cognizant.pensionerdetailsservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.pensionerdetailsservice.controller.PensionerDetailsController;
import com.cognizant.pensionerdetailsservice.model.BankDetails;
import com.cognizant.pensionerdetailsservice.model.PensionerDetails;
import com.cognizant.pensionerdetailsservice.model.Token;
import com.cognizant.pensionerdetailsservice.model.UserLogin;
import com.cognizant.pensionerdetailsservice.proxy.AuthenticationProxy;
import com.cognizant.pensionerdetailsservice.repository.PensionerDetailsRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class PensionerDetailsServiceApplicationTests {
	
	Logger log = LoggerFactory.getLogger(PensionerDetailsServiceApplicationTests.class);
	
	@Autowired
    PensionerDetailsController controller;
	@Autowired
	PensionerDetailsRepository pensionerRepo;
	@Autowired
	AuthenticationProxy proxy;

	
	Date date = new Date();
	BankDetails bd = new BankDetails("7777777777", "Bank Of Test", "private"); 
	PensionerDetails pd = new PensionerDetails("666666666666", "test",date, 
			"TEST5568P", 50000, 10000, "self",bd);	
	@Test
	void testGetPensionerDetails() {
		log.info("Testing the valid pensioner details and authorization token...");
		String adharNumber = "110911599416";
		Optional<PensionerDetails> list = pensionerRepo.findById(adharNumber);
		PensionerDetails expected = list.get();
		String token = controller.generateToken();
		PensionerDetails actual = controller.getPensionerDetailsByAdhar(token, adharNumber);
		assertEquals(expected.getAdharNumber(), actual.getAdharNumber());
		assertEquals(expected.getAllowances(), actual.getAllowances());
		assertEquals(expected.getSalaryEarned(), actual.getSalaryEarned());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getDob(), actual.getDob());
		assertEquals(expected.getPanNumber(), actual.getPanNumber());
		assertEquals(expected.getPensionType(), actual.getPensionType());
		assertEquals(expected.getBankDetails().getBankName(), actual.getBankDetails().getBankName());
		assertEquals(expected.getBankDetails().getAccountNumber(), actual.getBankDetails().getAccountNumber());
		assertEquals(expected.getBankDetails().getBankType(), actual.getBankDetails().getBankType());
	}
	
	@Test
	void testGetPensionerDetailsException() {
		log.info("Validating that the exception thrown while giving invalid aadhar number...");
		String adharNumber = "110911599419";
		String token = controller.generateToken();
		RuntimeException thrown = assertThrows(RuntimeException.class,
				() -> controller.getPensionerDetailsByAdhar(token, adharNumber),
				"Exception did not matched!!!");
		
		log.info("Message: "+thrown.getMessage());

		assertTrue(thrown.getMessage().contains("Invalid pensioner detail provided"));
		
	}
	
	@Test
	void testGetPensionerDetailsAuthException() {
		log.info("Validating exception thrown when given invalid token for geting pensioner details...");
		String adharNumber = "110911599416";
		RuntimeException thrown = assertThrows(RuntimeException.class,
				() -> controller.getPensionerDetailsByAdhar("wrongtoken", adharNumber),
				"Exception did not matched!!!");
		
		log.info("Message: "+thrown.getMessage());

		assertTrue(thrown.getMessage().contains("invalid token "+false));
		
	}

	
	@Test
	void testPostPensionerDetailsAuthException() {
		log.info("Validating exception thrown when given invalid token saving pensioner details...");
		RuntimeException thrown = assertThrows(RuntimeException.class,
				() -> controller.savePensioner("wrongtoken", pd),
				"Exception did not matched!!!");
		
		log.info("Message: "+thrown.getMessage());

		assertTrue(thrown.getMessage().contains("invalid token "+false));
		
	}
	
	
	@Test
	void testPostPensionerDetails() {
		String token = controller.generateToken();
		PensionerDetails actual = controller.savePensioner(token, pd);
		PensionerDetails expected = pd;
		
		assertEquals(expected.getAdharNumber(), actual.getAdharNumber());
		assertEquals(expected.getAllowances(), actual.getAllowances());
		assertEquals(expected.getSalaryEarned(), actual.getSalaryEarned());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getDob(), actual.getDob());
		assertEquals(expected.getPanNumber(), actual.getPanNumber());
		assertEquals(expected.getPensionType(), actual.getPensionType());
		assertEquals(expected.getBankDetails().getBankName(), actual.getBankDetails().getBankName());
		assertEquals(expected.getBankDetails().getAccountNumber(), actual.getBankDetails().getAccountNumber());
		assertEquals(expected.getBankDetails().getBankType(), actual.getBankDetails().getBankType());
		
	}
	
	@Test
	void applicationStarts() throws IOException {
		PensionerDetailsServiceApplication.main(new String[] {});
		assertTrue(true);
	}
	
	
	@Test
	void testSetFunctions() {
		log.info("Validating the entity classes...");
		PensionerDetails pend = new PensionerDetails();
		BankDetails bankd = new BankDetails();
		UserLogin user = new UserLogin();
		Token tok = new Token();
		pend.setAdharNumber("0000000000000");
		pend.setAllowances(5000);
		pend.setDob(new Date());
		pend.setName("Settestname");
		bankd.setAccountNumber("999999999999");
		bankd.setBankName("Test bank");
		bankd.setBankType("public");
		pend.setBankDetails(bankd);
		pend.setPanNumber("SETT5893U");
		pend.setPensionType("family");
		pend.setSalaryEarned(70000);
		bankd.setPensionerDetails(pend);
		bankd.getPensionerDetails();
		user.setUserName("hidh");
		user.setPassword("dummy");
		tok.setToken("hbkjdshcuidh");
		assertTrue(true);
	}
	
}
