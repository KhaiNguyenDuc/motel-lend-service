package com.my.motelApp.dto;

public class HomeDTO {

	private Long id;

	private String address;

	private String map;

	private float distance;

	private String name_chu;

	private boolean state;

	public HomeDTO(Long id, String address, String map, float distance, String name_chu, boolean state) {
		super();
		this.id = id;
		this.address = address;
		this.map = map;
		this.distance = distance;
		this.name_chu = name_chu;
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public HomeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
