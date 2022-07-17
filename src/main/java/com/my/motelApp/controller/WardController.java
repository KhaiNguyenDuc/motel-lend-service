package com.my.motelApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	private ResponseEntity<Home> addHomeByWardName(@RequestBody Home homeRequest) {
		
		Home homeResponse = wardService.addHome(homeRequest);
		return new ResponseEntity<>(homeResponse,HttpStatus.CREATED); 
	}

	@GetMapping("/{ward_id}/homes/")
	private ResponseEntity<List<Home>> getHomesByWardId(@PathVariable("ward_id") Long wardId) {
		List<Home> homeRespone = homeService.getHomesByWardId(wardId);
		return new ResponseEntity<>(homeRespone,HttpStatus.OK);
	}
	
	@PutMapping("/{ward_id}")
	private ResponseEntity<Ward> updateNameById(@PathVariable("ward_id") Long wardId,@RequestBody Ward wardRequest){
		Ward wardRespone = wardService.updateNameById(wardId, wardRequest);
		return new ResponseEntity<>(wardRespone,HttpStatus.OK);
	}

	@DeleteMapping("/{ward_id}")
	private  ResponseEntity<String> deleteById(@PathVariable("ward_id") Long wardId){
		String message = " Delete succesfully";
		wardService.delete(wardId);
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
}
