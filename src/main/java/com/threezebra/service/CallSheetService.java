package com.threezebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.domain.CallSheet;
import com.threezebra.domain.Department;
import com.threezebra.domain.JobRole;
import com.threezebra.domain.Unit;
import com.threezebra.domain.UserType;
import com.threezebra.repository.CallSheetRepository;

@Service
public class CallSheetService {
	
	@Autowired
	CallSheetRepository callSheetRepository;

	public CallSheet save(CallSheet callSheet) {
		callSheetRepository.save(callSheet);
		return callSheet;
	}

	public void deleteAll() {
	  callSheetRepository.deleteAll();
		
	}

	public List<CallSheet> findAll() {
		return callSheetRepository.findAll();
	}

	public void createCallSheet(Unit unit, List<Department> departmentlist, List<UserType> usertypeList,
			List<JobRole> jobRoleList) {
		// TODO Auto-generated method stub
		
	}

}
