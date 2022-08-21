package com.my.motelApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.motelApp.dto.JwtTokenResponse;
import com.my.motelApp.dto.LoginRequest;
import com.my.motelApp.security.JwtTokenProvider;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/sigin")
	public ResponseEntity<JwtTokenResponse> authenticateUser(
			@RequestBody LoginRequest LoginRequest){
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(LoginRequest.getUsername(),
														LoginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication); 
		String jwt = tokenProvider.generateToken(authentication);
		return new ResponseEntity<>(new JwtTokenResponse(jwt,"jwt"),HttpStatus.OK);
		
	}
}
