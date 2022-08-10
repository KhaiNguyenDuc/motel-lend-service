package com.my.motelApp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.motelApp.entity.Role;
import com.my.motelApp.entity.User;
import com.my.motelApp.exception.DataNotFoundException;
import com.my.motelApp.repository.RoleRepository;
import com.my.motelApp.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public Role getRoleById(Long roleId) {
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new DataNotFoundException("Not found role with id: "+roleId));
		return role;
	}

	@Override
	public Role addRole(Role roleRequest) {
		Role role = roleRepository.save(roleRequest);
		return role;
	}

	@Override
	public void deleteRoleById(Long roleId) {
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new DataNotFoundException("Not found role with id: "+roleId));
		List<User> user =  role.getUsers();
		
		for (User u : user) {
			u.setRoles(null);
		}
		roleRepository.delete(role);
	}

	@Override
	public Role updateRoleById(Long roleId, Role roleRequest) {
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new DataNotFoundException("Not found role with id: "+roleId));
		role.setName(roleRequest.getName());
		return roleRepository.save(role);
		
	}

}
