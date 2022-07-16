package com.my.motelApp.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "home")
public class Home {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private long id;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "map")
	private String map;
	
	@Column(name = "distance")
	private float distance;
	
	@Column(name = "name_chu")
	private String name_chu;
	
	@Column(name = "state")
	private boolean state;
	
	@OneToMany(mappedBy = "home",cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Image> img_phong = new  HashSet<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "info_id")
	private Info info;
	
	@ManyToOne
	@JoinColumn(name = "ward_id", nullable = true)
	private Ward ward;

	public long getId() {
		return id;
	}
	
	public Ward getWard() {
		return ward;
	}

	public void setWard(Ward ward) {
		this.ward = ward;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public String getName_chu() {
		return name_chu;
	}

	public void setName_chu(String name_chu) {
		this.name_chu = name_chu;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public Set<Image> getImg_phong() {
		return img_phong;
	}

	public void setImg_phong(Set<Image> img_phong) {
		this.img_phong = img_phong;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}
	
	public Home() {
		super();
	}
	
	public Home(String address, String map, float distance, String name_chu, boolean state, Info info) {
		super();
		this.address = address;
		this.map = map;
		this.distance = distance;
		this.name_chu = name_chu;
		this.state = state;
		this.info = info;
	}
	
}
