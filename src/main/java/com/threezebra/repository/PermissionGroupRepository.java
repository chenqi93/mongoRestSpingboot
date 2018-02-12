package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.PermissionGroup;

public interface PermissionGroupRepository extends MongoRepository<PermissionGroup, String> {

	PermissionGroup findByName(String permissiongroupname);
    PermissionGroup findById(long permissionlong);

}
