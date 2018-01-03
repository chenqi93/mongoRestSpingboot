package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.BaseLocation;

public interface BaseLocationRepository extends MongoRepository<BaseLocation, String>{
	
BaseLocation  findByNameContainingIgnoreCase(String name);

BaseLocation findById(long baseLocation);	

}
