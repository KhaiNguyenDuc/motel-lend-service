package com.my.motelApp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.my.motelApp.entity.Role;
import com.my.motelApp.entity.User;
import com.my.motelApp.exception.DataNotFoundException;
import com.my.motelApp.repository.UserRepository;
import com.my.motelApp.security.UserPrincipal;
import com.my.motelApp.service.CustomUserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, CustomUserDetailsService {

	@Autowired
	UserRepository userRepository;	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new DataNotFoundException("Can't find this user"));  

		List<Role> roles = userRepository.findRoleByUser(user);
		user.setRoles(roles);
		return UserPrincipal.create(user);
	}

	@Override
	public UserPrincipal loadById(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new DataNotFoundException("Can't find this user"));  

		List<Role> roles = userRepository.findRoleByUser(user);
		user.setRoles(roles);
		return UserPrincipal.create(user);
	}

}
