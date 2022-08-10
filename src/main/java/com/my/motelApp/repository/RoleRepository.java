package com.my.motelApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.motelApp.entity.Role;
import com.my.motelApp.entity.RoleName;




public interface RoleRepository extends JpaRepository<Role, Long> {

	boolean existsByName(String name);

	Optional<Role> findByName(RoleName admin);

}
