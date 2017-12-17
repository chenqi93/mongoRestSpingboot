package com.threezebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.domain.SpecialRole;
import com.threezebra.domain.SpecialRoleMapping;
import com.threezebra.repository.SpecialRoleMappingRepsitory;
@Service
public class SpecialRoleMappingService {
	@Autowired
	SpecialRoleMappingRepsitory specialRoleMappingRepository;

	

	public SpecialRoleMapping findBySpecialRole(SpecialRole specialRole) {
		return specialRoleMappingRepository.findBySpecialRole(specialRole);
	}



	public SpecialRoleMapping save(SpecialRoleMapping specialRoleMapping) {
		return specialRoleMappingRepository.save(specialRoleMapping);
		
	}



	public List<SpecialRoleMapping> findAll() {
		return specialRoleMappingRepository.findAll();
	}

}
