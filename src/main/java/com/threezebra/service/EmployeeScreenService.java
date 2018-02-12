package com.threezebra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.domain.EmployeeScreen;
import com.threezebra.repository.EmployeeScreenRepository;

@Service
public class EmployeeScreenService {
	
	
	@Autowired
	private EmployeeScreenRepository employeeScreenRepoository;
	
	public  EmployeeScreen save(EmployeeScreen employeeScreen) {
		return employeeScreenRepoository.save(employeeScreen);
	}

	public EmployeeScreen findByName(String name) {
		return employeeScreenRepoository.findByName(name);
	}

}
