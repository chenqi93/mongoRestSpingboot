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
	
	public  DistributionGroup save(DistributionGroup distributionGroup) {
		return distributionGroupRepository.save(distributionGroup);
	}

	public List<DistributionGroup> findAll() {
		return distributionGroupRepository.findAll();
	}
	
	public List<DistributionGroup> findByDaily(String daily) {
		return distributionGroupRepository.findByDaily(daily);
	}
     public void usertypelist() {
	 distributionGroupRepository.deleteAll();
 }

	public DistributionGroup findByName(String distributionGroup) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteAll() {
		distributionGroupRepository.deleteAll();
		
	}

	public DistributionGroup createDistributionGroup(String distributionGroup,long unit,long department,long userType, long jobrole) {
		DistributionGroup distributionGroupObj=new DistributionGroup();
		Unit unitObj=unitService.findbyId(unit);
		Department dept=departmentService.findById(department);
		UserType userTyp=userTypeService.findById(userType);
		JobRole jobRoleobj=jobRoleService.findById(jobrole);
		distributionGroupObj.setId(System.nanoTime());
		distributionGroupObj.setName(distributionGroup);
		distributionGroupObj.setUnit(unitObj);
		distributionGroupObj.setDepartment(dept);
		distributionGroupObj.setJobRole(jobRoleobj);
		return distributionGroupRepository.save(distributionGroupObj);
	}
}
