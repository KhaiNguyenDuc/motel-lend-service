package com.my.motelApp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.motelApp.entity.Home;
import com.my.motelApp.entity.Ward;
import com.my.motelApp.repository.DescriptionRepository;
import com.my.motelApp.repository.HomeRepository;
import com.my.motelApp.repository.ImageRepository;
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
	private ImageRepository imageRepository;
	
	@Autowired
	private InfoRepository infoRepository;
	
	@Autowired
	private DescriptionRepository descriptionRepository;
	
	@Override
	public List<Home> getAllHomes() {
		return homeRepository.findAll();
	}

	@Override
	public List<Home> getHomesByWardId(Long wardId) {
		Ward ward = wardRepository.findById(wardId).get();
		return homeRepository.findByWard(ward);
	}



	

}
