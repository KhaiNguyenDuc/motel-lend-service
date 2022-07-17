package com.my.motelApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.motelApp.config.Constant;
import com.my.motelApp.entity.Home;
import com.my.motelApp.service.HomeService;

@RestController
@RequestMapping("api/v1/homes")
public class HomeController {
	
	@Autowired
	HomeService homeService;
	
	@GetMapping
	private ResponseEntity<List<Home>> getAllHomes(){
		List<Home> homeResponse = homeService.getAllHomes();
		return new ResponseEntity<>(homeResponse,HttpStatus.OK);
	}
	
	@DeleteMapping("/{home_id}")
	private ResponseEntity<String> deleteHomeById(@PathVariable("home_id") Long homeId){
		homeService.deleteById(homeId);
		return new ResponseEntity<>(Constant.DELETE_SUCCESSFULLY,HttpStatus.OK);
	}
	
	@PutMapping("/{home_id}")
	private ResponseEntity<Home> updateHomeById(
			@PathVariable("home_id") Long homeId, @RequestBody Home homeRequest){
		Home homeResponse = homeService.updateById(homeId,homeRequest);
		return new ResponseEntity<>(homeResponse,HttpStatus.OK);
	}
}
