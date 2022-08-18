package com.cognizant.pensionerdetailsservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.pensionerdetailsservice.model.Token;
import com.cognizant.pensionerdetailsservice.model.UserLogin;

//Feign client authentication proxy 
@FeignClient(name = "auth-service", url = "${AUTHENTICATION_SERVICE_PROXY:http://localhost:8084}")
public interface AuthenticationProxy {

	@GetMapping("/auth/validate")
	public boolean getValidity(
			@RequestHeader(name = "Authorization") String token1);
	
	@PostMapping("/auth/login")
	public Token login(
			@RequestBody UserLogin userLoginCredentials);
		
}
