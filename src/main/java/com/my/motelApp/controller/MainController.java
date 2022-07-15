package com.my.motelApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.motelApp.entity.Description;
import com.my.motelApp.service.DescriptionService;

@RestController
//http://localhost:8080/api/v1
@RequestMapping("api/v1/") 
public class MainController {
	
	@Autowired
	DescriptionService descriptionService;
	
	@GetMapping("descriptions")
	public List<Description> getAllDescriptions() {
		return descriptionService.getAllDescriptions();
	}
}
