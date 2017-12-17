package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.Unit;

public interface UnitRepository extends MongoRepository<Unit, String>{
	Unit findById(long id);	
	Unit findByName(String name);
}
