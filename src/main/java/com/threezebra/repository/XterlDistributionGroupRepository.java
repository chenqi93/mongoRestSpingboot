package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.XternalDistributionGroup;

public interface XterlDistributionGroupRepository extends MongoRepository<XternalDistributionGroup,String> {

	XternalDistributionGroup findByName(String name);

}
