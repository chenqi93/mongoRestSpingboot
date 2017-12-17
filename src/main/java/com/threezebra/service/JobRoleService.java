package com.threezebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.domain.Department;
import com.threezebra.domain.JobRole;
import com.threezebra.domain.UserType;
import com.threezebra.repository.JobRoleRepository;

@Service("jobRoleService")
public class JobRoleService {
	 @Autowired 
	 JobRoleRepository  jobRoleRepository;
	 
	 public JobRole update(UserType userType,String name) {

			JobRole jobRole = jobRoleRepository.findByName(name);
			if (null != jobRole) {
				jobRole.setName(name);
				jobRole.setUserType(userType);
				jobRoleRepository.save(jobRole);
				return jobRole;
			} else {
				JobRole jobRoleobj = new JobRole();
				jobRoleobj.setId(System.nanoTime());
				jobRoleobj.setName(name);
				jobRoleobj.setUserType(userType);
				jobRoleRepository.save(jobRoleobj);
				return jobRoleobj;
			}
  
 }
	 public List<JobRole> findAll() {
	        return jobRoleRepository.findAll();
	    }
	public List<JobRole> findByUserType(UserType userType) {
		
		return jobRoleRepository.findByUserType(userType);
	}
	public void delete(JobRole jobRole) {
		jobRoleRepository.delete(jobRole);
		
	}
	public JobRole findByName(String jobRole) {
		return jobRoleRepository.findByName(jobRole);
	}
	
	public JobRole findById(long jobRole) {
		return jobRoleRepository.findById(jobRole);
	}

	
	
	 
	
}
