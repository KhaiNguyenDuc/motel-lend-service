package com.my.motelApp.serviceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.motelApp.config.Constant;
import com.my.motelApp.entity.Home;
import com.my.motelApp.entity.Ward;
import com.my.motelApp.exception.DataExistException;
import com.my.motelApp.exception.DataNotFoundException;
import com.my.motelApp.repository.HomeRepository;
import com.my.motelApp.repository.WardRepository;
import com.my.motelApp.service.WardService;

@Service
public class WardServiceImpl implements WardService {

	@Autowired
	private HomeRepository homeRepository;
	
	@Autowired
	private WardRepository wardRepository;
	
	@Override
	public Home addHome(Long wardId, Home home) {
		
		Optional<Ward> wardOpt = wardRepository.findById(wardId);
		if(wardOpt.isEmpty()) {
			throw new DataNotFoundException(Constant.messageNotFound(wardId));
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
		Ward verifyWard = wardRepository.findByName(wardRequest.getName());
		if (wardOtp.isEmpty()) {
			throw new DataNotFoundException(Constant.messageNotFound(wardId));
		}
		if (Objects.nonNull(verifyWard)) {
			throw new DataExistException("Name already exist in database");
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
			throw new DataNotFoundException(Constant.messageNotFound(wardId));
		}
	}

	

	@Override
	public Ward createWard(Ward ward) {
		
		Ward wardOpt = wardRepository.findByName(ward.getName());
		if (!Objects.isNull(wardOpt)) {
			throw new DataExistException("Data already exist in database");
		}
		Ward wardSaved = wardRepository.save(new Ward(ward.getName()));
		return wardSaved;
	}

	@Override
	public Ward getWardById(Long wardId) {
		Optional<Ward> wardOpt = wardRepository.findById(wardId);
		if (Objects.isNull(wardOpt)) {
			throw new DataNotFoundException(Constant.messageNotFound(wardId));
		}
		return wardOpt.get();
	}

}
