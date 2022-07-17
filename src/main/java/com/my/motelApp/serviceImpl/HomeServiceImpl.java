package com.my.motelApp.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.motelApp.config.Constant;
import com.my.motelApp.entity.Home;
import com.my.motelApp.entity.Ward;
import com.my.motelApp.exception.DataNotFoundException;
import com.my.motelApp.repository.HomeRepository;
import com.my.motelApp.repository.WardRepository;
import com.my.motelApp.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	private HomeRepository homeRepository;
	
	@Autowired
	private WardRepository wardRepository;
	

	@Override
	public List<Home> getAllHomes() {
		return homeRepository.findAll();
	}

	@Override
	public List<Home> getHomesByWardId(Long wardId) {
		Ward ward = wardRepository.findById(wardId).get();
		return homeRepository.findByWard(ward);
	}
	@Override
	public void deleteById(Long homeId) {
		try {
			homeRepository.deleteById(homeId);
		}
		catch(Exception e){
			throw new DataNotFoundException(Constant.messageNotFound(homeId));
		}
	}

	@Override
	public Home updateById(Long homeId, Home homeRequest) {
		
		Optional<Home> homeOpt = homeRepository.findById(homeId);
		
		if (homeOpt.isEmpty()) {
			throw new DataNotFoundException(Constant.messageNotFound(homeId));
		}
		Home homeData = homeOpt.get();
		
		homeData.convert(homeRequest);
		
		return homeRepository.save(homeData);
	}


}
