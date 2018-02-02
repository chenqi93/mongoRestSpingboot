package com.threezebra.service;

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
		jobRole.setCheckFlag(checkFlag);
		jobRole.setName(name);
		return jobRoleRepository.save(jobRole);
	}

	public boolean containsName(final List<Unit> unitlist, final String name) {
		return unitlist.stream().filter(o -> o.getName().equals(name)).findFirst().isPresent();
	}

	public JobRole update(JobRole jobRole,Unit unit, UserType userType, String checkFlag) {
		List<Unit> unitlist = jobRole.getUnit();
		if (checkFlag.equals("TRUE")) {
			unitlist.add(unit);
			jobRole.setUnit(unitlist);
		} else {
			unitlist.remove(unit);
			jobRole.setUnit(unitlist);
		}
		jobRole.setUserType(userType);
		jobRole.setCheckFlag(checkFlag);
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

}
