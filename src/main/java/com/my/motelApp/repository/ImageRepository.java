package com.my.motelApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.my.motelApp.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}