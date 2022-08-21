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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.motelApp.config.Constant;
import com.my.motelApp.dto.PageResponse;
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
	public ResponseEntity<PageResponse<Ward>> getAllWards(
			@RequestParam("page") Integer page,
			@RequestParam("size") Integer size){
		
		PageResponse<Ward> wardsResponse = wardService.getAllWards(page,size);
		
		return new ResponseEntity<>(wardsResponse,HttpStatus.OK);
	}
	
	@GetMapping("/{ward_id}")
	public ResponseEntity<Ward> getWardByid(@PathVariable("ward_id") Long wardId) {
		Ward ward = wardService.getWardById(wardId);
		return new ResponseEntity<>(ward,HttpStatus.OK);
	}
	
	@GetMapping("/{ward_id}/homes")
	public ResponseEntity<List<Home>> getHomesByWardId(@PathVariable("ward_id") Long wardId) {
		List<Home> homeRespone = homeService.getHomesByWardId(wardId);
		return new ResponseEntity<>(homeRespone,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Ward> createWard(@RequestBody Ward wardRequest){
		Ward wardResponse =  wardService.createWard(wardRequest);
		return new ResponseEntity<>(wardResponse,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/{ward_id}/homes")
	public ResponseEntity<Home> addHomeByWardId(@PathVariable("ward_id") Long wardId, @RequestBody Home homeRequest) {
		
		Home homeResponse = wardService.addHome(wardId,homeRequest);
		return new ResponseEntity<>(homeResponse,HttpStatus.CREATED); 
	}
	
	
	
	@PutMapping("/{ward_id}")
	public ResponseEntity<Ward> updateNameById(@PathVariable("ward_id") Long wardId,@RequestBody Ward wardRequest){
		Ward wardRespone = wardService.updateNameById(wardId, wardRequest);
		return new ResponseEntity<>(wardRespone,HttpStatus.OK);
	}

	@DeleteMapping("/{ward_id}")
	public ResponseEntity<String> deleteById(@PathVariable("ward_id") Long wardId){
		wardService.delete(wardId);
		return new ResponseEntity<>(Constant.DELETE_SUCCESSFULLY,HttpStatus.OK);
	}
	
	
	
}
