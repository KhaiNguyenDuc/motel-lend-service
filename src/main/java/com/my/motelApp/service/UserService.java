package com.my.motelApp.service;

import java.util.List;

import com.my.motelApp.dto.ApiResponse;
import com.my.motelApp.dto.UserRequest;
import com.my.motelApp.dto.UserResponse;
import com.my.motelApp.security.UserPrincipal;

public interface UserService {


	UserResponse getUserByUsername(String username);

	List<UserResponse> getAllUser();

	ApiResponse createUser(UserRequest userRequest);

	UserResponse updateUserByUsername(String username, UserRequest userRequest, UserPrincipal currentUser);

	void deleteUserByUsername(String username);

	ApiResponse giveAdmin(String username);

	ApiResponse takeAdmin(String username);

}
