package com.my.motelApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.motelApp.entity.Info;

public interface InfoRepository extends JpaRepository<Info, Long> {

	List<Info> findByHome(Long homeId);

}
