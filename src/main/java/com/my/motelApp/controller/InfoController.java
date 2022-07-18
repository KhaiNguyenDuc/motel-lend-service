package com.my.motelApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.motelApp.config.Constant;
import com.my.motelApp.entity.Description;
import com.my.motelApp.entity.Info;
import com.my.motelApp.service.InfoService;

@RestController
@RequestMapping("api/v1/infos")
public class InfoController {

	@Autowired
	InfoService infoService;
	
	@GetMapping("/{info_id}")
	public ResponseEntity<Info> getInfoById(@PathVariable("info_id") Long infoId) {
		Info info = infoService.getInfoById(infoId);
		return new ResponseEntity<>(info,HttpStatus.OK);
	}
	
	@GetMapping("/{info_id}/descriptions")
	public ResponseEntity<List<Description>> getDescriptionsById(
			@PathVariable("info_id") Long infoId){
		List<Description> descriptions = infoService.getDescriptionsById(infoId);
		return new ResponseEntity<>(descriptions,HttpStatus.OK);
	}
	
	@PostMapping("/{info_id}/descriptions")
	public ResponseEntity<Info> addDescription(
			@PathVariable("info_id") Long infoId, @RequestBody Description desRequest){
		Info info = infoService.addDescription(infoId,desRequest);
		return new ResponseEntity<>(info,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{info_id}/descriptions")
	public ResponseEntity<String> deleteAllDescriptionById(
			@PathVariable("info_id") Long infoId){
		infoService.deleteAllDescriptionById(infoId);
		return new ResponseEntity<>(Constant.DELETE_SUCCESSFULLY,HttpStatus.CREATED);
	}
	// delete descriptions
}
