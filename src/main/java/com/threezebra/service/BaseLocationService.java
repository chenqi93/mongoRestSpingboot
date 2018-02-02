package com.threezebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.configuration.ApplicationConfigurationProperties;
import com.threezebra.domain.BaseLocation;
import com.threezebra.repository.BaseLocationRepository;

@Service("baseLocationService")
public class BaseLocationService {
	@Autowired
	private ApplicationConfigurationProperties configurationProperties;

	@Autowired
	private BaseLocationRepository baseLocationRepository;

	public BaseLocation save(String name) {
		BaseLocation baselocation = baseLocationRepository
				.findByNameContainingIgnoreCase(name);	
			if (null != baselocation) {
				baselocation.setName(baselocation.getName());
				return baseLocationRepository.save(baselocation);
			} else {
				BaseLocation loc=new BaseLocation();
				loc.setId(System.nanoTime());
				loc.setName(name);
				return baseLocationRepository.save(loc);
			}
		
	}

	public List<BaseLocation> findAll() {
		return baseLocationRepository.findAll();
	}
	public BaseLocation findByName(String name) {
		return baseLocationRepository.findByNameContainingIgnoreCase(name);
	}

	public BaseLocation findbyId(long baseLocation) {
		// TODO Auto-generated method stub
		return baseLocationRepository.findById(baseLocation);
	}
	
}
