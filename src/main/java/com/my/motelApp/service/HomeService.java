package com.my.motelApp.service;

import java.util.List;

import com.my.motelApp.entity.Home;
import com.my.motelApp.entity.Image;
import com.my.motelApp.entity.Info;

public interface HomeService {

	List<Home> getAllHomes();

	void deleteById(Long homeId);

	List<Home> getHomesByWardId(Long wardId);

	Home updateById(Long homeId, Home homeRequest);

	Info getInfoByHomeId(Long homeId);

	List<Image> getImageByHomeId(Long homeId);

	Home getHomesById(Long homeId);

	void deleteInfoById(Long homeId);




}
