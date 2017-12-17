package com.threezebra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.domain.DistributionGroup;
import com.threezebra.domain.DistributionGroupMapping;
import com.threezebra.repository.DistributionGroupMappingRepository;

@Service
public class DistributionGroupMappingService {
	
	
	@Autowired
	DistributionGroupMappingRepository distributionGroupMappingRepository;

	public DistributionGroupMapping findBydistributionGroup(DistributionGroup distributionGroup) {
		return distributionGroupMappingRepository.findBydistributionGroup(distributionGroup);
	}

	public DistributionGroupMapping save(DistributionGroupMapping distributionGroupMapping) {
		return  distributionGroupMappingRepository.save(distributionGroupMapping);
	}
	
}
