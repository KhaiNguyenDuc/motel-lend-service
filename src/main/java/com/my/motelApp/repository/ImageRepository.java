package com.my.motelApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.motelApp.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
