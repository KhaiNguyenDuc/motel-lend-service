package com.my.motelApp.service;

import java.util.List;

import com.my.motelApp.entity.Role;

public interface RoleService {

	List<Role> getAllRoles();

	Role getRoleById(Long roleId);

	Role addRole(Role roleRequest);

	void deleteRoleById(Long roleId);

	Role updateRoleById(Long roleId, Role roleRequest);

}
