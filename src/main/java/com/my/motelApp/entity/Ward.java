package com.my.motelApp.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ward")
public class Ward {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "ward")
	private Set<Home> homes = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Home> getHomes() {
		return homes;
	}

	public void setHomes(Set<Home> homes) {
		this.homes = homes;
	}

	public long getId() {
		return id;
	}

	public Ward(String name) {
		super();
		this.name = name;
	}
	
	
}