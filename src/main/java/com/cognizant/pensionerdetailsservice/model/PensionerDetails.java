package com.cognizant.pensionerdetailsservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//Entity class for pensioner details
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class PensionerDetails {
	
	@Id
	private String adharNumber;
	private String name;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dob;
	private String panNumber;
	private int salaryEarned;
	private int allowances;
	private String pensionType;
	
	@OneToOne(fetch = FetchType.EAGER)
	private BankDetails bankDetails;
	
}
