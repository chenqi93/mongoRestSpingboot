package com.threezebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.domain.AdditionalLocation;
import com.threezebra.repository.AdditionalLocationRepository;
@Service
public class AdditionalLocationService {
	
	@Autowired
	AdditionalLocationRepository addtionalLocationRepository;
	
	public AdditionalLocation  save(AdditionalLocation additionalLocation) {
		 return addtionalLocationRepository.save(additionalLocation);
		
	}
	
	public AdditionalLocation  findByName(long additionalLocationId) {
		 return addtionalLocationRepository.findById(additionalLocationId);
		
	}

	public List<AdditionalLocation> findAll() {
		// TODO Auto-generated method stub
		return addtionalLocationRepository.findAll();
	}

}
