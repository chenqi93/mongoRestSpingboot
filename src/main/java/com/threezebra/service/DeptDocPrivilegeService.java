package com.threezebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.domain.DeptDocPrivilege;
import com.threezebra.repository.DeptDocRepository;

@Service
public class DeptDocPrivilegeService {
	
	@Autowired
	private DeptDocRepository deptDocRepository;

	public DeptDocPrivilege save(DeptDocPrivilege deptDocPrivilege) {
		 	return deptDocRepository.save(deptDocPrivilege) ;
	}

	public List<DeptDocPrivilege> findAll() {
		return deptDocRepository.findAll();
	}

	public DeptDocPrivilege findByName(String deptdoc) {
		// TODO Auto-generated method stub
		return deptDocRepository.findByName(deptdoc);
	} 
	
	public DeptDocPrivilege findById(long deptdocId) {
		// TODO Auto-generated method stub
		return deptDocRepository.findById(deptdocId);
	} 

}
