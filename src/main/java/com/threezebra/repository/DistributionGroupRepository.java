package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.DistributionGroup;

public interface DistributionGroupRepository   extends MongoRepository<DistributionGroup, String>{
	DistributionGroup findByNameContainingIgnoreCase(String distributionGroup);

}
