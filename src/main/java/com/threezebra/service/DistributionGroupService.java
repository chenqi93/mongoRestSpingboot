package com.threezebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.domain.Department;
import com.threezebra.domain.DistributionGroup;
import com.threezebra.domain.JobRole;
import com.threezebra.domain.Unit;
import com.threezebra.domain.UserType;
import com.threezebra.repository.DistributionGroupRepository;

@Service
public class DistributionGroupService {

	@Autowired
	DistributionGroupRepository distributionGroupRepository;

	@Autowired
	UnitService unitService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	UserTypeService userTypeService;
	@Autowired
	JobRoleService jobRoleService;

	public DistributionGroup save(DistributionGroup distributionGroup) {
		return distributionGroupRepository.save(distributionGroup);
	}

	public List<DistributionGroup> findAll() {
		return distributionGroupRepository.findAll();
	}

	public void usertypelist() {
		distributionGroupRepository.deleteAll();
	}

	public DistributionGroup findByName(String distributionGroup) {
		return distributionGroupRepository.findByNameContainingIgnoreCase(distributionGroup);
	}

	public void deleteAll() {
		distributionGroupRepository.deleteAll();

	}

	public DistributionGroup createDistributionGroup(String defaultValue,List<String> location, String distributionGroup, List<Unit> unitlist,
			List<Department> departmentlst, List<UserType> userTypelst, List<JobRole> jobRolelst, String employeeListflag,String isActive) {
		DistributionGroup distributionGroupObj = distributionGroupRepository.findByNameContainingIgnoreCase(distributionGroup);
		if (null != distributionGroupObj) {
			distributionGroupObj.setDefaultvalue(defaultValue);
			distributionGroupObj.setLocation(location);
			distributionGroupObj.setName(distributionGroup);
			distributionGroupObj.setUnitlist(unitlist);
			distributionGroupObj.setDeptlist(departmentlst);
			distributionGroupObj.setUserType(userTypelst);
			distributionGroupObj.setEmployeeListflag(employeeListflag);
			distributionGroupObj.setJobRole(jobRolelst);
			distributionGroupObj.setIsActive(isActive);
		} else {
			distributionGroupObj = new DistributionGroup();
			distributionGroupObj.setId(System.nanoTime());
			distributionGroupObj.setLocation(location);
			distributionGroupObj.setDefaultvalue(defaultValue);
			distributionGroupObj.setName(distributionGroup);
			distributionGroupObj.setUnitlist(unitlist);
			distributionGroupObj.setDeptlist(departmentlst);
			distributionGroupObj.setEmployeeListflag(employeeListflag);
			distributionGroupObj.setUserType(userTypelst);
			distributionGroupObj.setJobRole(jobRolelst);
			distributionGroupObj.setIsActive(isActive);
		}

		return distributionGroupRepository.save(distributionGroupObj);
	}
}
