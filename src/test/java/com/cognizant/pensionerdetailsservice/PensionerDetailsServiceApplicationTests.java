package com.cognizant.pensionerdetailsservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.pensionerdetailsservice.controller.PensionerDetailsController;
import com.cognizant.pensionerdetailsservice.model.BankDetails;
import com.cognizant.pensionerdetailsservice.model.PensionerDetails;
import com.cognizant.pensionerdetailsservice.repository.PensionerDetailsRepository;

@SpringBootTest
class PensionerDetailsServiceApplicationTests {
	
	Logger log = LoggerFactory.getLogger(PensionerDetailsServiceApplicationTests.class);
	
	@Autowired
	PensionerDetailsController controller;
	@Autowired
	PensionerDetailsRepository pensionerRepo;
	
	
	Date date = new Date();
	BankDetails bd = new BankDetails("7777777777", "Bank Of Test", "private"); 
	PensionerDetails pd = new PensionerDetails("666666666666", "test",date, 
			"TEST5568P", 50000, 10000, "self",bd);
		
	String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTY2MDUyMjE4MywiaWF0IjoxNjYwNTIwMzgzfQ.Pd5rmDT-cT5j23blmtMrCJLlWHW7-2ciKFprsfLGC_8";

	@Test
	void contextLoads() {
	}
	
	@Test
	void testGetPensionerDetails() {
		String adharNumber = "110911599416";
		Optional<PensionerDetails> list = pensionerRepo.findById(adharNumber);
		PensionerDetails expected = list.get();
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
		String adharNumber = "110911599419";
		RuntimeException thrown = assertThrows(RuntimeException.class,
				() -> controller.getPensionerDetailsByAdhar(token, adharNumber),
				"Exception did not matched!!!");
		
		log.info("Message: "+thrown.getMessage());

		assertTrue(thrown.getMessage().contains("Invalid pensioner detail provided"));
		
	}
	
	@Test
	void testGetPensionerDetailsAuthException() {
		String adharNumber = "110911599416";
		
		
		RuntimeException thrown = assertThrows(RuntimeException.class,
				() -> controller.getPensionerDetailsByAdhar("wrongtoken", adharNumber),
				"Exception did not matched!!!");
		
		log.info("Message: "+thrown.getMessage());

		assertTrue(thrown.getMessage().contains("invalid token "+false));
		
	}

	
	@Test
	void testPostPensionerDetailsAuthException() {
		RuntimeException thrown = assertThrows(RuntimeException.class,
				() -> controller.savePensioner("wrongtoken", pd),
				"Exception did not matched!!!");
		
		log.info("Message: "+thrown.getMessage());

		assertTrue(thrown.getMessage().contains("invalid token "+false));
		
	}
	
	
	@Test
	void testPostPensionerDetails() {
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
		PensionerDetails pend = new PensionerDetails();
		BankDetails bankd = new BankDetails();
		pend.setAdharNumber("4444444444444444");
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
		assertTrue(true);
	}
	
}
