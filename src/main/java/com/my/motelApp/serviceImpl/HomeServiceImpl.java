package com.my.motelApp.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.motelApp.config.Constant;
import com.my.motelApp.entity.Home;
import com.my.motelApp.entity.Image;
import com.my.motelApp.entity.Info;
import com.my.motelApp.entity.Ward;
import com.my.motelApp.exception.DataNotFoundException;
import com.my.motelApp.repository.HomeRepository;
import com.my.motelApp.repository.InfoRepository;
import com.my.motelApp.repository.WardRepository;
import com.my.motelApp.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	private HomeRepository homeRepository;

	@Autowired
	private WardRepository wardRepository;

	@Autowired
	InfoRepository infoRepository;
	
	
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
		} catch (Exception e) {
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

	@Override
	public Info getInfoByHomeId(Long homeId) {
		Optional<Home> home = homeRepository.findById(homeId);
		if (home.isEmpty()) {
			throw new DataNotFoundException(Constant.messageNotFound(homeId));
		}
		Info info = home.get().getInfo();
		return info;
	}

	@Override
	public List<Image> getImageByHomeId(Long homeId) {
		Optional<Home> home = homeRepository.findById(homeId);
		if (home.isEmpty()) {
			throw new DataNotFoundException(Constant.messageNotFound(homeId));
		}
		List<Image> images = home.get().getImg_phong();
		return images;
	}

	@Override
	public Home getHomesById(Long homeId) {
		Optional<Home> home = homeRepository.findById(homeId);
		if(home.isEmpty()) {
			throw new DataNotFoundException(Constant.messageNotFound(homeId));
		}
		return home.get();
	}

	@Override
	public void deleteInfoById(Long homeId) {
		Optional<Home> homeOpt = homeRepository.findById(homeId);
		if(homeOpt.isEmpty()) {
			throw new DataNotFoundException(Constant.messageNotFound(homeId));
		}
		Home home = homeOpt.get();
		
		Info info = home.getInfo();
		home.removeInfo(info);
		infoRepository.delete(info);
	}
}
