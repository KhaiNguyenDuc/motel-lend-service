package com.my.motelApp.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.my.motelApp.dto.ApiResponse;
import com.my.motelApp.dto.UserRequest;
import com.my.motelApp.dto.UserResponse;
import com.my.motelApp.entity.Role;
import com.my.motelApp.entity.RoleName;
import com.my.motelApp.entity.User;
import com.my.motelApp.exception.AccessDenyException;
import com.my.motelApp.exception.DataExistException;
import com.my.motelApp.exception.DataNotFoundException;
import com.my.motelApp.repository.RoleRepository;
import com.my.motelApp.repository.UserRepository;
import com.my.motelApp.security.UserPrincipal;
import com.my.motelApp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserResponse getUserByUsername(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new DataNotFoundException("Not found user with username : "+ username));
		System.out.println(user.getUsername());
		return modelMapper.map(user, UserResponse.class);
	}

	@Override
	public List<UserResponse> getAllUser() {
		List<User> users = userRepository.findAll();
		return Arrays.asList( modelMapper.map(users,UserResponse[].class));
	}

	@Override
	public ApiResponse createUser(UserRequest userRequest) {
		
		String username = userRequest.getUsername();
		
		if(userRepository.existsByUsername(username)) {
			throw new DataExistException("This user with username: "+username+" already exist");
		}
		
		Role role = roleRepository.findByName(RoleName.USER)
				.orElseThrow(() -> new DataNotFoundException("This role User not yet created"));
		
		User userSaved = modelMapper.map(userRequest, User.class);
		userSaved.setRoles(Arrays.asList(role));
		userSaved.setPassword(passwordEncoder.encode(userSaved.getPassword()));
		userRepository.save(userSaved);
		return new ApiResponse("Create successfully", HttpStatus.CREATED);
	}

	@Override
	public UserResponse updateUserByUsername(String username, UserRequest userRequest, UserPrincipal currentUser) {
		
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new DataExistException(username));
		
		if(currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ADMIN.toString()))
			|| currentUser.getUsername().equals(username)){
				modelMapper.map(userRequest, user);
				userRepository.save(user);
				return modelMapper.map(user, UserResponse.class);
			}
		throw new AccessDenyException("You don't have permission to update this user");
		
	}

	@Override
	public void deleteUserByUsername(String username) {
		
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new DataNotFoundException("Not found user with username: "+username));
		
		userRepository.delete(user);
		
	}

	@Override
	public ApiResponse giveAdmin(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new DataNotFoundException("Not found user with username: "+username));
		
		for(Role r : user.getRoles()) {
			if(r.getName().equals(RoleName.ADMIN)) {
				throw new DataExistException("This user already admin");
			}
		}
		
		Role role = roleRepository.findByName(RoleName.ADMIN)
				.orElseThrow(() -> new DataNotFoundException("Not found role with admin in database"));
		
		user.addRoles(role);
		userRepository.save(user);
		return new ApiResponse("Sucessfully give this user admin",HttpStatus.OK);
	}

	@Override
	public ApiResponse takeAdmin(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new DataNotFoundException("Not found user with username: "+username));
		List<Role> roles = user.getRoles().stream()
				.filter(role -> role.getName().equals(RoleName.ADMIN))
				.collect(Collectors.toList());
		
		user.removeRole(roles.get(0));
		userRepository.save(user);
		return new ApiResponse("Sucessfully take this user admin",HttpStatus.OK);
	}
	
	


}
