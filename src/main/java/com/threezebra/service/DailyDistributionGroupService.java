package com.threezebra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.domain.DailyDistributionGroup;
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
		return dailyDistributionGroupRepository.findByName(name);
	}

}
