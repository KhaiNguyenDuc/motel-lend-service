package com.my.motelApp.service;

import com.my.motelApp.dto.PageResponse;
import com.my.motelApp.entity.Home;
import com.my.motelApp.entity.Ward;

public interface WardService {

	Home addHome(Long wardId, Home home);

	Ward updateNameById(Long wardId, Ward wardRequest);

	void delete(Long wardId);

	Ward createWard(Ward ward);

	Ward getWardById(Long wardId);

	PageResponse<Ward> getAllWards(Integer page, Integer size);


}
