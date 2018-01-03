package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.PermissionGroup;

public interface PermissionGroupRepository extends MongoRepository<PermissionGroup, String> {

	PermissionGroup findByNameContainingIgnoreCase(String permissiongroupname);
    PermissionGroup findById(long permissionlong);

}
