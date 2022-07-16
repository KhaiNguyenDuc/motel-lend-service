package com.my.motelApp.service;

import java.util.List;

import com.my.motelApp.entity.Home;
import com.my.motelApp.entity.Ward;

public interface WardService {

	void addHome(Home home);

	List<Ward> getAllWards();

}
