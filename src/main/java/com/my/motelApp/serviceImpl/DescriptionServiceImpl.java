package com.my.motelApp.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.motelApp.config.Constant;
import com.my.motelApp.entity.Description;
import com.my.motelApp.exception.DataNotFoundException;
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

	@Override
	public void deleteDesById(Long desId) {
		Optional<Description> desOpt = descriptionRepository.findById(desId);
		if(desOpt.isEmpty()) {
			throw new DataNotFoundException(Constant.messageNotFound(desId));
		}
		// delete a father
		
	}

	
}
