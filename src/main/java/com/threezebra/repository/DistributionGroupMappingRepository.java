package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.DistributionGroup;
import com.threezebra.domain.DistributionGroupMapping;

public interface DistributionGroupMappingRepository extends MongoRepository<DistributionGroupMapping,String> {

	DistributionGroupMapping findBydistributionGroup(DistributionGroup distributionGroup);

	
}
