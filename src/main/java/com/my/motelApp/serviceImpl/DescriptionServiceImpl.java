package com.my.motelApp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.motelApp.entity.Description;
import com.my.motelApp.repository.DescriptionRepository;
import com.my.motelApp.service.DescriptionService;

@Service
public class DescriptionServiceImpl implements DescriptionService{

	@Autowired
	DescriptionRepository descriptionRepository;
	
	@Override
	public List<Description> getAllDescriptions() {
		return descriptionRepository.findAll();
	}

	
}
