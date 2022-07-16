package com.my.motelApp.service;

import java.util.List;

import com.my.motelApp.entity.Home;
import com.my.motelApp.entity.Ward;

public interface HomeService {

	List<Home> getAllHomes();


	List<Home> getHomesByWardId(Long wardId);



}