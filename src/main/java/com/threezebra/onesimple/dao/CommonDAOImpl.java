package com.threezebra.onesimple.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.threezebra.domain.Application;
import com.threezebra.domain.Department;
import com.threezebra.domain.DeptDocPrivilege;
import com.threezebra.domain.DistributionGroup;
import com.threezebra.domain.JobRole;
import com.threezebra.domain.OtherDocPrivilege;
import com.threezebra.domain.SharedDocPrivilege;
import com.threezebra.domain.SpecialRole;
import com.threezebra.domain.Unit;
import com.threezebra.domain.UserType;

@Component
public class CommonDAOImpl {
	
	

	public List<Unit> getUnits(){
		List<Unit> unitList = new ArrayList<>();
		return unitList;
	}
	
	public List<Department> getDepartments(){
		List<Department> deptList = new ArrayList<>();
		return deptList;
	}
	
	public List<UserType> getUserTypes(){
		List<UserType> userTypeList = new ArrayList<>();
		
		return userTypeList;
	}
	
	public List<JobRole> getJobRolesForUserType(String userType){
		List<JobRole> jobRoleList = new ArrayList<>();
		 return jobRoleList;
	}
	
	

	public List<Application> getApplications() {
		List<Application> applicationList = new ArrayList<>();	
		return applicationList;
	}
	
	public List<SpecialRole> getSpecialRoles(){
		List<SpecialRole> unitList = new ArrayList<>();
		 return unitList;
	}

	public List<DistributionGroup> getDistributionGroups() {
		List<DistributionGroup> distributionGroups = new ArrayList<>();
		 return distributionGroups;
	}

	public String saveGroupDetails(DistributionGroup groupVO) {
		
		return "save";
	}

	public List<DeptDocPrivilege> getDeptDocPrivileges() {
		List<DeptDocPrivilege> deptDocPrivList = new ArrayList<>();
		 return deptDocPrivList;
	}

	public List<SharedDocPrivilege> getSharedDocPrivileges() {
		List<SharedDocPrivilege> shareDocPrivList = new ArrayList<>();
		return shareDocPrivList;
	}

	public List<OtherDocPrivilege> getOtherDocPrivileges() {
		List<OtherDocPrivilege> otherDocPrivList = new ArrayList<>();
		
		return otherDocPrivList;
	}

	public List<JobRole> getJobRolesForUserType() {
		List<JobRole> jobRoleList = new ArrayList<>();
		
		return jobRoleList;
	}
	
	public List<Department> getDepartments(String unitId){
		List<Department> deptList = new ArrayList<>();
		
		return deptList;
	}

	public List<UserType> getUserTypes(String deptId) {
		List<UserType> userTypeList = new ArrayList<>();
		return userTypeList;
	}
}
