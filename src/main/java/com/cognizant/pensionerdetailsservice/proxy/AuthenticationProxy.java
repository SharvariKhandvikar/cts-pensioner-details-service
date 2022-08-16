package com.cognizant.pensionerdetailsservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service", url = "localhost:8084")
public interface AuthenticationProxy {

	@GetMapping("/validate")
	public boolean getValidity(
			@RequestHeader(name = "Authorization") String token1);
	
}
