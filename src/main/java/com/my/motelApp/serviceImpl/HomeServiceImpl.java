package com.my.motelApp.serviceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.motelApp.entity.Description;
import com.my.motelApp.entity.Home;
import com.my.motelApp.entity.Image;
import com.my.motelApp.entity.Info;
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
	public List<Home> getHomesByWardName(String wardName) {
		Ward ward = wardRepository.findByName(wardName);
		return homeRepository.findHomeByWard(ward);
	}

	@Override
	public Home addHome(Home home) {
		
		
		// save images
		Set<Image> images = home.getImg_phong();
		for (Image image : images) {
			image.setHome(home);
			imageRepository.save(image);
		}
		
		// save description
		Info info = home.getInfo();
		Set<Description> descriptions = info.getDescriptions();
		for (Description description : descriptions) {
			description.setInfo(info);
			descriptionRepository.save(description);
		}
		
		homeRepository.save(home);
		return null;
	}

}
