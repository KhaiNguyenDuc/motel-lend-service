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
import com.my.motelApp.entity.Ward;
import com.my.motelApp.service.HomeService;
import com.my.motelApp.service.WardService;

@RestController
@RequestMapping("api/v1/wards")
public class WardController {

	@Autowired
	HomeService homeService;

	@Autowired
	WardService wardService;

	@GetMapping
	private List<Ward> getAllWards(){
		return wardService.getAllWards();
	}
	


	@PostMapping("/homes")
	private void addHomeByWardName(@RequestBody Home home) {
		wardService.addHome(home);
	}

	@GetMapping("/{ward_id}/homes/")
	private List<Home> getHomesByWardId(@PathVariable("ward_id") Long wardId) {
		return homeService.getHomesByWardId(wardId);
	}

}
