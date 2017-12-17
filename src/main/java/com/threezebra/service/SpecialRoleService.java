package com.threezebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.domain.BaseLocation;
import com.threezebra.domain.SpecialRole;
import com.threezebra.repository.SpecialRoleRepository;
@Service
public class SpecialRoleService {
	
	@Autowired
	SpecialRoleRepository specialRoleRepository;
	@Autowired
	BaseLocationService baseLocationService;
	
	public SpecialRole save(SpecialRole specialRole) {
 		return specialRoleRepository.save(specialRole);
 }
	 public List<SpecialRole> findAll() {
	        return specialRoleRepository.findAll();
	    }
	
	public SpecialRole findByName(long specialRole) {
	
		return specialRoleRepository.findById(specialRole);
	}
	public SpecialRole findById(Long specialRoleLong) {
		
		return specialRoleRepository.findById(specialRoleLong);
	}
	public SpecialRole createSpecialRole(String location,String emplistFlag,String name,String desc) {
		SpecialRole specialRole=new SpecialRole();
			specialRole = new SpecialRole();
			specialRole.setId(System.nanoTime());
			specialRole.setSpecialRoleFlag(emplistFlag);
			BaseLocation locationObj=baseLocationService.findByName(location);
			specialRole.setBaseLocation(locationObj);
			specialRole.setName(name);
			specialRole.setSpecialJobRoleDesc(desc);
		  return specialRoleRepository.save(specialRole);
	}
}
