package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.SpecialRole;
import com.threezebra.domain.SpecialRoleMapping;

public interface SpecialRoleMappingRepsitory extends MongoRepository<SpecialRoleMapping,String>{

	SpecialRoleMapping findBySpecialRole(SpecialRole specialRole);
    SpecialRoleMapping findById(long id);

}
