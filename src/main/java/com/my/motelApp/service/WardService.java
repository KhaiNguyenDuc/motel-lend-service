package com.my.motelApp.service;

import java.util.List;

import com.my.motelApp.entity.Home;
import com.my.motelApp.entity.Ward;

public interface WardService {

	Home addHome(Long wardId, Home home);

	List<Ward> getAllWards();

	Ward updateNameById(Long wardId, Ward wardRequest);

	void delete(Long wardId);

	Ward createWard(Ward ward);

}
