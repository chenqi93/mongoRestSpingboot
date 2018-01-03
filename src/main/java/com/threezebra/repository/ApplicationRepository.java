package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.Application;

public interface ApplicationRepository  extends MongoRepository<Application, String>{
	Application findById(long id);

	Application findByNameContainingIgnoreCase(String name);
}
