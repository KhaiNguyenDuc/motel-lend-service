package com.my.motelApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.motelApp.entity.Info;

public interface InfoRepository extends JpaRepository<Info, Long> {

}
