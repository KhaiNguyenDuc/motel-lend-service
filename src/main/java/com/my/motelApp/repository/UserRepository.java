package com.my.motelApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.motelApp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
