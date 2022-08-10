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

import com.my.motelApp.config.Constant;
import com.my.motelApp.entity.Role;
import com.my.motelApp.service.RoleService;

@RestController
@RequestMapping("api/v1/roles")
public class RoleController {

	@Autowired
	RoleService roleService;
	
	@GetMapping
	public ResponseEntity<List<Role>> getAllRoles(){
		List<Role> roles = roleService.getAllRoles();
		return new ResponseEntity<>(roles,HttpStatus.OK);
	}
	
	@GetMapping("/{role_id}")
	public ResponseEntity<Role> getRoleById(@PathVariable("role_id") Long roleId){
		Role role = roleService.getRoleById(roleId);
		return new ResponseEntity<>(role,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Role> addRole(@RequestBody Role roleRequest){
		Role roleResponse = roleService.addRole(roleRequest);
		return new ResponseEntity<>(roleResponse,HttpStatus.OK);
	}

	@DeleteMapping("/{role_id}")
	public ResponseEntity<String> deleteRoleById(@PathVariable("role_id") Long roleId){
		roleService.deleteRoleById(roleId);
		return new ResponseEntity<>(Constant.DELETE_SUCCESSFULLY,HttpStatus.OK);
	}
	
	@PutMapping("/{role_id}")
	public ResponseEntity<Role> updateRoleById(
			@PathVariable("role_id") Long roleId,
			@RequestBody Role roleRequest){
		Role roleResponse = roleService.updateRoleById(roleId,roleRequest);
		return new ResponseEntity<>(roleRequest,HttpStatus.OK);
	}
	
	
}
