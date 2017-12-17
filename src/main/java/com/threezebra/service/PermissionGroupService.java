package com.threezebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.configuration.ApplicationConfigurationProperties;
import com.threezebra.domain.PermissionGroup;
import com.threezebra.repository.PermissionGroupRepository;

@Service("permissionGroupService")
public class PermissionGroupService {
	 @Autowired
	    private ApplicationConfigurationProperties configurationProperties;
	   @Autowired 
	    private PermissionGroupRepository permissionGroupRepository;
	   
	   public PermissionGroup save(PermissionGroup permissionGroup) {
		
		   return permissionGroupRepository.save(permissionGroup);
	    }

	public void deleteAll() {
		permissionGroupRepository.deleteAll();
		
	}

	public List<PermissionGroup> findAll() {
		return permissionGroupRepository.findAll();
	}

	public PermissionGroup findByName(String permissiongroupname) {
		return permissionGroupRepository.findByName(permissiongroupname);
	}



	public PermissionGroup findById(long permissionlong) {
		return permissionGroupRepository.findById(permissionlong);
	}
	   
	   
}
