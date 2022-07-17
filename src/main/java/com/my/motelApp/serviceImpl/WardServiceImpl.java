package com.my.motelApp.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.motelApp.entity.Home;
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
	public Home addHome(Home home) {
		
		// Save Home
		Long wardId = home.getWard().getId();
		Optional<Ward> wardOpt = wardRepository.findById(wardId);
		if(wardOpt.isEmpty()) {
			throw new DataNotFoundException(messageNotFound(wardId));
		}
		Ward ward = wardOpt.get();
		home.setWard(ward);
		return homeRepository.save(home);
		

	}

	@Override
	public List<Ward> getAllWards() {
		return wardRepository.findAll();
	}

	@Override
	public Ward updateNameById(Long wardId ,Ward wardRequest) {
		
		Optional<Ward> wardOtp = wardRepository.findById(wardId);
		if (wardOtp.isEmpty()) {
			throw new DataNotFoundException(messageNotFound(wardId));
		}
		Ward wardData = wardOtp.get();
		wardData.setName(wardRequest.getName());
		return wardRepository.save(wardData);
	}

	@Override
	public void delete(Long wardId) {
		try {
			wardRepository.deleteById(wardId);
		}
		catch(Exception e){
			throw new DataNotFoundException(messageNotFound(wardId));
		}
	}

	public static String messageNotFound(Long wardId) {
		return "This ward with id "+ wardId+ " not found";
	}

}
