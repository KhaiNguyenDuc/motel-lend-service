package com.my.motelApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.motelApp.entity.Ward;

public interface WardRepository extends JpaRepository<Ward, Long> {

	Ward findByName(String wardName);



}
