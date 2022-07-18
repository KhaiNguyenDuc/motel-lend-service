package com.my.motelApp.service;

import java.util.List;

import com.my.motelApp.entity.Description;

public interface DescriptionService{

	List<Description> getAllDescriptions();

	void deleteDesById(Long desId);

}
