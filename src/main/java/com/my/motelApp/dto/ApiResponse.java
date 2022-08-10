package com.my.motelApp.dto;

import org.springframework.http.HttpStatus;

public class ApiResponse {

	private String message;
	private HttpStatus http;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getHttp() {
		return http;
	}
	public void setHttp(HttpStatus http) {
		this.http = http;
	}
	public ApiResponse(String message, HttpStatus http) {
		super();
		this.message = message;
		this.http = http;
	}
	
	
}
