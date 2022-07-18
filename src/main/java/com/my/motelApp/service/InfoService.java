package com.my.motelApp.service;

import java.util.List;

import com.my.motelApp.entity.Description;
import com.my.motelApp.entity.Info;

public interface InfoService {

	Info getInfoById(Long infoId);

	List<Description> getDescriptionsById(Long infoId);

	Info addDescription(Long infoId, Description desRequest);

	void deleteAllDescriptionById(Long infoId);


	
	

}
