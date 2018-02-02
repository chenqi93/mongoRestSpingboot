package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.JobRole;

public interface JobRoleRepository extends MongoRepository<JobRole, String> {
    	JobRole findById(long id);

	JobRole findByNameContainingIgnoreCase(String name);

		
}
