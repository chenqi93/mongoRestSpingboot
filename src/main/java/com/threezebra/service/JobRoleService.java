package com.threezebra.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.domain.JobRole;
import com.threezebra.domain.Unit;
import com.threezebra.domain.UserType;
import com.threezebra.repository.JobRoleRepository;

@Service("jobRoleService")
public class JobRoleService {
	@Autowired
	JobRoleRepository jobRoleRepository;

	public JobRole save(String name, UserType userType, List<Unit> unit, String checkFlag) {
		JobRole jobRole = new JobRole();
		jobRole.setId(System.nanoTime());
		jobRole.setUnit(unit);
		jobRole.setUserType(userType);
		jobRole.setUserTypeName(userType.getName());
		jobRole.setCheckFlag(checkFlag);
		jobRole.setName(name);
		return jobRoleRepository.save(jobRole);
	}

	public boolean containsName(final List<Unit> unitlist, final String name) {
		return unitlist.stream().filter(o -> o.getName().equals(name)).findFirst().isPresent();
	}

	public JobRole update(JobRole jobRole,Unit unit, UserType userType) {
		List<Unit> unitlist = jobRole.getUnit();
		if (jobRole.getCheckFlag().equals("TRUE")){ 
			if(containsName(unitlist, unit.getName())==false) {
		
			    unitlist.add(unit);
				jobRole.setUnit(unitlist);
			}
			
			}
		else {
				Iterator<Unit> itr=unitlist.iterator();
                while(itr.hasNext()) {
                 Unit unitobj=(Unit)itr.next();
                 if(unitobj.getName().equals(unit.getName())){
                	 itr.remove();
                 }
                
                }
                jobRole.setUnit(unitlist);	
				
			  }
			
		jobRole.setUserType(userType);
        return jobRoleRepository.save(jobRole);
	}

	public List<JobRole> findAll() {
		return jobRoleRepository.findAll();
	}

	public void delete(JobRole jobRole) {
		jobRoleRepository.delete(jobRole);

	}

	public JobRole findByName(String jobRole) {
		return jobRoleRepository.findByNameContainingIgnoreCase(jobRole);
	}

	public JobRole findById(long jobRole) {
		return jobRoleRepository.findById(jobRole);
	}

	public List<JobRole> findByUserType(UserType userType) {
		// TODO Auto-generated method stub
		return jobRoleRepository.findByUserTypeName(userType.getName());
	}

	public JobRole updateFlag(JobRole jobRole, String flag) {
		jobRole.setCheckFlag(flag);
		return jobRoleRepository.save(jobRole);
	}

}
