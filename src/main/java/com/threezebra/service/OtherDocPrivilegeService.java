package com.threezebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.domain.OtherDocPrivilege;
import com.threezebra.repository.OtherDocPrivilegeRepository;

@Service
public class OtherDocPrivilegeService {
	
	@Autowired
	OtherDocPrivilegeRepository otherDocRepository;

	public OtherDocPrivilege save(OtherDocPrivilege otherDocPrivilege) {
	
		return otherDocRepository.save(otherDocPrivilege);
	}

	public List<OtherDocPrivilege> findAll() {
		
		return otherDocRepository.findAll();
	}

	public OtherDocPrivilege findByName(String otherdocprev) {
		
		return otherDocRepository.findByName(otherdocprev);
	}
	
public OtherDocPrivilege findById(long otherdocprevId) {
		
		return otherDocRepository.findById(otherdocprevId);
	}
	

}
