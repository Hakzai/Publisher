package com.akeir.publisher.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akeir.publisher.security.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	private final AuthenticationService authService;
	
	public AuthenticationController(AuthenticationService authService)
	{
		this.authService = authService;
	}
	
	@PostMapping("/login")
	public String authenticate(Authentication auth)
	{
		return authService.authenticate(auth);
	}
}
