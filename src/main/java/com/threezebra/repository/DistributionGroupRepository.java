package com.threezebra.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.DistributionGroup;

public interface DistributionGroupRepository   extends MongoRepository<DistributionGroup, String>{

	List<DistributionGroup> findByDaily(String daily);

}
