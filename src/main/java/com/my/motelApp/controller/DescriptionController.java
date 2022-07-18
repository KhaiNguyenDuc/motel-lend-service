package com.my.motelApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.motelApp.config.Constant;
import com.my.motelApp.service.DescriptionService;

@RestController
@RequestMapping("api/v1/descriptions")
public class DescriptionController {

	@Autowired
	DescriptionService descriptionService;
	
	@DeleteMapping("/{des_id}")
	public ResponseEntity<String> deleteDesById(@PathVariable("des_id") Long desId) {
		descriptionService.deleteDesById(desId);
		return new ResponseEntity<>(Constant.DELETE_SUCCESSFULLY, HttpStatus.OK);
	}
}
