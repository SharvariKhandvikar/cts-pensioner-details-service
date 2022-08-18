package com.cognizant.pensionerdetailsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.cognizant.pensionerdetailsservice.proxy.AuthenticationProxy;

@SpringBootApplication
@EnableFeignClients(clients = {AuthenticationProxy.class})
public class PensionerDetailsServiceApplication {
	
	public static void main(String[] args) {
	
		SpringApplication.run(PensionerDetailsServiceApplication.class, args);	
	}

}
