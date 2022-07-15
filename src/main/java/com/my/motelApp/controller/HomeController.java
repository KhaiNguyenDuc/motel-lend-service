package com.my.motelApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.motelApp.entity.Home;
import com.my.motelApp.service.HomeService;

@RestController
@RequestMapping("api/v1/homes")
public class HomeController {
	
	@Autowired
	HomeService homeService;
	
	@GetMapping
	private List<Home> getAllHomes(){
		return homeService.getAllHomes();
	}
	
	@GetMapping("/{ward_name}")
	private List<Home> getHomesByWardName(
			@PathVariable("ward_name") String wardName){
		return homeService.getHomesByWardName(wardName);
	}
	
	@PostMapping
	private Home addHome(@RequestBody Home home) {
		return homeService.addHome(home);
	}
}
