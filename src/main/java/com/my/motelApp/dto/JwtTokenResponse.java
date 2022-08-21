package com.my.motelApp.dto;

public class JwtTokenResponse {

	private String token;
	private String type = "jwt";
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public JwtTokenResponse(String token, String type) {
		this.token = token;
		this.type = type;
	};
	
}
