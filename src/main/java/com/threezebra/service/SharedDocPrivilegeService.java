package com.threezebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.configuration.ApplicationConfigurationProperties;
import com.threezebra.domain.SharedDocPrivilege;
import com.threezebra.repository.SharedDocPrivilegeRepository;

@Service("sharedDocPrivilegeService")
public class SharedDocPrivilegeService {
	@Autowired
	private ApplicationConfigurationProperties configurationProperties;
	@Autowired
	private SharedDocPrivilegeRepository sharedDocPrivilegeRepository;
	
	public SharedDocPrivilege save(SharedDocPrivilege sharedDocPrivilege ) {
		return sharedDocPrivilegeRepository.save(sharedDocPrivilege);
	}

	public List<SharedDocPrivilege> findAll() {
		return sharedDocPrivilegeRepository.findAll();
	}
	
	public SharedDocPrivilege findSharedDocPrivilege(String shareDocPrivilege) {
		return sharedDocPrivilegeRepository.findByName(shareDocPrivilege);
	}
	public SharedDocPrivilege findById(long shareDocPrivilege) {
		return sharedDocPrivilegeRepository.findById(shareDocPrivilege);
	}
	public void deleteSharedDocPrivilege(SharedDocPrivilege sharedDocPrivilege) {
		sharedDocPrivilegeRepository.delete(sharedDocPrivilege);
	}
}
