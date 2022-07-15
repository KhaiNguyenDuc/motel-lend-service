package com.my.motelApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.my.motelApp.entity.Home;
import com.my.motelApp.entity.Ward;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long>{

	List<Home> findHomeByWard(Ward ward);

}
