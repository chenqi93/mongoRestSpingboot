package com.threezebra.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.JobRole;
import com.threezebra.domain.UserType;

public interface JobRoleRepository extends MongoRepository<JobRole, String> {
    	JobRole findById(long id);

	JobRole findByNameContainingIgnoreCase(String name);

	List<JobRole> findByUserTypeName(String userType);

		
}
