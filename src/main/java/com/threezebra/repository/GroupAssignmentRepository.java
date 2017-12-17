package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.DistributionGroupMapping;

public interface GroupAssignmentRepository extends MongoRepository<DistributionGroupMapping,String>{

}
