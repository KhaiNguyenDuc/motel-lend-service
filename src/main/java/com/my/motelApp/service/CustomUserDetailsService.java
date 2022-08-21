package com.my.motelApp.service;

import com.my.motelApp.security.UserPrincipal;

public interface CustomUserDetailsService {
	
	UserPrincipal loadById(Long userId);
	
}
