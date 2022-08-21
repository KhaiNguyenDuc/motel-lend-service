package com.my.motelApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.motelApp.config.Constant;
import com.my.motelApp.dto.ApiResponse;
import com.my.motelApp.dto.UserRequest;
import com.my.motelApp.dto.UserResponse;
import com.my.motelApp.security.CurrentUser;
import com.my.motelApp.security.UserPrincipal;
import com.my.motelApp.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

	// apiResponse
	@Autowired
	UserService userService;
	
	@GetMapping("/{username}")
	public ResponseEntity<UserResponse> getUserByUsername(@PathVariable("username") String username){
		UserResponse userResponse = userService.getUserByUsername(username);
		return new ResponseEntity<>(userResponse,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponse>> getAllUser(){
		List<UserResponse> usersResponse = userService.getAllUser();
		return new ResponseEntity<>(usersResponse,HttpStatus.OK);
	}
	
	@PutMapping("/{username}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<UserResponse> updateUserByUsername(
			@PathVariable("username") String username,
			@RequestBody UserRequest userRequest,
			@CurrentUser UserPrincipal currentUser){
		UserResponse userResponse = userService.updateUserByUsername(username,userRequest,currentUser);
		return new ResponseEntity<>(userResponse,HttpStatus.OK);
	}
	
	@DeleteMapping("/{username}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteUserByUsername(@PathVariable("username") String username){
		userService.deleteUserByUsername(username);
		return new ResponseEntity<>(Constant.DELETE_SUCCESSFULLY,HttpStatus.OK);
	}
	
	@PutMapping("/give-admin/{username}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> giveAdmin(@PathVariable("username") String username){
		ApiResponse response =  userService.giveAdmin(username);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	} 
	
	@PutMapping("/take-admin/{username}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> takeAdmin(@PathVariable("username") String username){
		ApiResponse response =  userService.takeAdmin(username);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	} 
	
	
}
