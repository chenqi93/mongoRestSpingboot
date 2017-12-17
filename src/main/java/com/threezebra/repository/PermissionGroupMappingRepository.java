package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.PermissionGroup;
import com.threezebra.domain.PermissionGroupMapping;

public interface PermissionGroupMappingRepository  extends MongoRepository<PermissionGroupMapping,String>{

	PermissionGroupMapping findByPermissionGroup(PermissionGroup permissionGroup);

}
