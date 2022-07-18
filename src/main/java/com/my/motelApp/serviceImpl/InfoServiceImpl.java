package com.my.motelApp.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.motelApp.config.Constant;
import com.my.motelApp.entity.Description;
import com.my.motelApp.entity.Info;
import com.my.motelApp.exception.DataExistException;
import com.my.motelApp.exception.DataNotFoundException;
import com.my.motelApp.repository.DescriptionRepository;
import com.my.motelApp.repository.InfoRepository;
import com.my.motelApp.service.InfoService;

@Service
public class InfoServiceImpl implements InfoService {

	@Autowired
	InfoRepository infoRepository;
	
	@Autowired
	DescriptionRepository descriptionRepository;
	
	@Override
	public Info getInfoById(Long infoId) {
		Optional<Info> info = infoRepository.findById(infoId);
		if(info.isEmpty()) {
			throw  new DataNotFoundException(Constant.messageNotFound(infoId));
		}
		return info.get();
	}

	@Override
	public List<Description> getDescriptionsById(Long infoId) {
		Optional<Info> info = infoRepository.findById(infoId);
		if(info.isEmpty()) {
			throw  new DataNotFoundException(Constant.messageNotFound(infoId));
		}
		return info.get().getDescriptions();
	}

	@Override
	public Info addDescription(Long infoId, Description desRequest) {
		Optional<Info> infoOpt = infoRepository.findById(infoId);
		if(infoOpt.isEmpty()) {
			throw new DataNotFoundException(Constant.messageNotFound(infoId));
		}
		
		Info info = infoOpt.get();
		
		String content = desRequest.getContent();
		for (Description des : info.getDescriptions()) {
			if (des.getContent().equals(content)) {
				throw new DataExistException("This description already exist");
			}
		}
		
		info.addDescriptions(desRequest);
		return infoRepository.save(info);
	}

	@Override
	public void deleteAllDescriptionById(Long infoId) {
		Optional<Info> infoOpt = infoRepository.findById(infoId);
		if(infoOpt.isEmpty()) {
			throw new DataNotFoundException(Constant.messageNotFound(infoId));
		}
		Info info = infoOpt.get();
		
		List<Description> description = info.getDescriptions();
		Integer length = description.size();
		int i = 0;
		while(length >0) {
			System.out.println(description.size());
			System.out.println(i);
			System.out.println(description.get(i));
			info.removeDescriptions(description.get(i));
			length = length - 1;
		}
		descriptionRepository.deleteAll(description);
		 
	}


	

	
}
