package com.my.motelApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.my.motelApp.entity.Role;
import com.my.motelApp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT r FROM Role r INNER JOIN r.users u WHERE u = ?1 ")
	List<Role> findRoleByUser(User user);

	Optional<User> findByUsername(String username);

	boolean existsByUsername(String username);

}
