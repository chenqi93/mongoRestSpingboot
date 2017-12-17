package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.AdditionalLocation;

public interface AdditionalLocationRepository extends MongoRepository<AdditionalLocation, String>{
	AdditionalLocation findById(long id);	
}
