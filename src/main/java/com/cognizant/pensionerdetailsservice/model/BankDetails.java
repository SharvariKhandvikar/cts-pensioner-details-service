package com.cognizant.pensionerdetailsservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//Entity class for bank details
@Getter
@Setter
@NoArgsConstructor
@ToString
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

	public BankDetails(String accountNumber, String bankName, String bankType) {
		super();
		this.accountNumber = accountNumber;
		this.bankName = bankName;
		this.bankType = bankType;
	}
	
	

}
