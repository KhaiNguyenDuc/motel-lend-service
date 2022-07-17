package com.my.motelApp.service;

import java.util.List;

import com.my.motelApp.entity.Home;

public interface HomeService {

	List<Home> getAllHomes();

	void deleteById(Long homeId);

	List<Home> getHomesByWardId(Long wardId);

	Home updateById(Long homeId, Home homeRequest);



}
