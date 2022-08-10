package com.my.motelApp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user",
	   uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "password")
	@JsonIgnore
	private String password;
	
	@Column(name = "enabled")
	private boolean enabled;

	@ManyToMany
	@JoinTable(
			name = "user_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	private List<Role> roles = new ArrayList<>();
	
	public void setId(Long id) {
		this.id = id;
	}


	public Long getId() {
		return id;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public User(Long id, String username, String email, String phoneNumber, String password, boolean enabled,
			List<Role> roles) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.enabled = enabled;
		this.roles = roles;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", password=" + password + ", enable=" + enabled + ", roles=" + roles + "]";
	}


	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		if(roles == null) {
			this.roles = null;
		}else {
			this.roles = roles;
		}
		
	}

	public void addRoles(Role role) {
		this.roles.add(role);
	}
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@PreRemove
	public void removeListRole() {
		this.roles.clear();
	}
	
	public void removeRole(Role role) {
		this.roles.remove(role);
	}



	
	
}
