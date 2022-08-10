package com.my.motelApp.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.my.motelApp.entity.Role;
import com.my.motelApp.entity.User;

public class UserPrincipal implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1320585925823790211L;

	private String id;
	private String username;
	private String email;
	private String password;
	private String phoneNumber;
	
	private boolean enabled;
	private List<GrantedAuthority> roles = new ArrayList<>();
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public UserPrincipal(String username, String email, String password, String phoneNumber, boolean enabled,
			List<GrantedAuthority> roles) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.enabled = enabled;
		this.roles = roles;
	}

	public static UserPrincipal create(User user) {
		List<Role> rolesFecth = user.getRoles();
		List<GrantedAuthority> roles = new ArrayList<>();
		for (Role r : rolesFecth) {
			roles.add(new SimpleGrantedAuthority("ROLE_"+r.getName()));
		}
		
		return new UserPrincipal(
				user.getUsername(),
				user.getEmail(),
				PasswordEncoderConfig
						.passwordEncoder()
						.encode(user.getPassword()),
				user.getPhoneNumber(),
				user.isEnabled(),
				roles
				);
		
	
	}
	
	

}
