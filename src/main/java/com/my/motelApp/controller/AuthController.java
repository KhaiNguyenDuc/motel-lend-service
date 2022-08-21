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

import com.my.motelApp.dto.ApiResponse;
import com.my.motelApp.dto.JwtTokenResponse;
import com.my.motelApp.dto.LoginRequest;
import com.my.motelApp.dto.UserRequest;
import com.my.motelApp.security.JwtTokenProvider;
import com.my.motelApp.service.UserService;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/signin")
	public ResponseEntity<JwtTokenResponse> authenticateUser(
			@RequestBody LoginRequest LoginRequest){
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(LoginRequest.getUsername(),
														LoginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication); 
		String jwt = tokenProvider.generateToken(authentication);
		return new ResponseEntity<>(new JwtTokenResponse(jwt,"jwt"),HttpStatus.OK);
		
	}
	
	@PostMapping("/signup")
	public ResponseEntity<ApiResponse> registerUser(
			@RequestBody UserRequest userRequest){
		
		ApiResponse response = userService.createUser(userRequest);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
		
	}
}
