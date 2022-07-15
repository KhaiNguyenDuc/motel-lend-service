package com.my.motelApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.motelApp.entity.Description;

public interface DescriptionRepository extends JpaRepository<Description, Long>{
	
	
}
