package com.threezebra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.domain.PermissionGroup;
import com.threezebra.domain.PermissionGroupMapping;
import com.threezebra.repository.PermissionGroupMappingRepository;

@Service
public class PermissionGroupMappingService {

	
	@Autowired
	PermissionGroupMappingRepository permissionGroupMappingRepository;

	public PermissionGroupMapping findByPermissionGroup(PermissionGroup permissionGroup) {
	 	return permissionGroupMappingRepository.findByPermissionGroup(permissionGroup);
	}

	public PermissionGroupMapping save(PermissionGroupMapping permissionGroupMappingobj) {
		return permissionGroupMappingRepository.save(permissionGroupMappingobj);
		
	}

	
	
}
