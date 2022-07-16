package com.my.motelApp.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.motelApp.entity.Description;
import com.my.motelApp.entity.Home;
import com.my.motelApp.entity.Image;
import com.my.motelApp.entity.Info;
import com.my.motelApp.entity.Ward;
import com.my.motelApp.exception.DataNotFoundException;
import com.my.motelApp.repository.DescriptionRepository;
import com.my.motelApp.repository.HomeRepository;
import com.my.motelApp.repository.ImageRepository;
import com.my.motelApp.repository.WardRepository;
import com.my.motelApp.service.WardService;

@Service
public class WardServiceImpl implements WardService {

	@Autowired
	private HomeRepository homeRepository;
	
	@Autowired
	private WardRepository wardRepository;
	
	@Autowired
	private ImageRepository imageRepository;

	
	@Autowired
	private DescriptionRepository descriptionRepository;
	
	@Override
	public void addHome(Home home) {
		
		// Save Home
		Optional<Ward> wardOpt = wardRepository.findById(home.getWard().getId());
		if(wardOpt.isEmpty()) {
			throw new DataNotFoundException("This ward with id "+home.getWard().getId()+
					" not found");
		}
		Ward ward = wardOpt.get();
		home.setWard(ward);
		homeRepository.save(home);
		
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
		
	}

	@Override
	public List<Ward> getAllWards() {
		return wardRepository.findAll();
	}
	

}
