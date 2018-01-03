package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.SpecialRole;

public interface SpecialRoleRepository extends MongoRepository<SpecialRole, String> {
	SpecialRole findByNameContainingIgnoreCase(String spRoleName);
    SpecialRole findById(long id);	
}
