package com.threezebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.domain.DailyDistributionGroup;
import com.threezebra.domain.DistributionGroup;
import com.threezebra.repository.DailyDistributionGroupRepository;

@Service
public class DailyDistributionGroupService {
	
	@Autowired
	DailyDistributionGroupRepository  dailyDistributionGroupRepository;

	public DailyDistributionGroup save(DailyDistributionGroup dailyDistributionGroup) {
	   return	dailyDistributionGroupRepository.save(dailyDistributionGroup);
		
	}

	public void deleteAll() {
	  dailyDistributionGroupRepository.deleteAll();
		
	}

	public DailyDistributionGroup findByName(String name) {
		// TODO Auto-generated method stub
		return dailyDistributionGroupRepository.findByNameContainingIgnoreCase(name);
	}

	public List<DailyDistributionGroup> findAll() {
		// TODO Auto-generated method stub
		return dailyDistributionGroupRepository.findAll();
	}

}
