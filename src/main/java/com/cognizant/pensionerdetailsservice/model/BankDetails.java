package com.cognizant.pensionerdetailsservice.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankDetails {
	
	@Id
	private String accountNumber;
	private String bankName;
	private String bankType;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "bankDetails")
	@JsonIgnore
	private PensionerDetails pensionerDetails;
	
	public BankDetails() {}
	
	public BankDetails(String accountNumber, String bankName, String bankType) {
		super();
		this.accountNumber = accountNumber;
		this.bankName = bankName;
		this.bankType = bankType;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public PensionerDetails getPensionerDetails() {
		return pensionerDetails;
	}

	public void setPensionerDetails(PensionerDetails pensionerDetails) {
		this.pensionerDetails = pensionerDetails;
	}


}
