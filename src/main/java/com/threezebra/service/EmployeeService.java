package com.threezebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.threezebra.configuration.ApplicationConfigurationProperties;
import com.threezebra.domain.EmpDetail;
import com.threezebra.repository.EmployeeRepository;

@Service("employeeService")
public class EmployeeService {
	 @Autowired
	    private ApplicationConfigurationProperties configurationProperties;
	   @Autowired 
	    private EmployeeRepository employeeRepository;
	    
	    public EmpDetail save(EmpDetail empDetail) {
	    		return employeeRepository.save(empDetail);
	    }

	    public List<EmpDetail> findAll() {
	        return employeeRepository.findAll();
	    }

		public EmpDetail findById(long id) {
			return employeeRepository.findById(id);
		}

}
