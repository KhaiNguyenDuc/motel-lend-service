package com.my.motelApp.service;

import java.util.List;

import com.my.motelApp.entity.Home;

public interface HomeService {

	List<Home> getAllHomes();

	List<Home> getHomesByWardName(String wardName);

	Home addHome(Home home);

}
