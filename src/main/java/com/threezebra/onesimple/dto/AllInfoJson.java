package com.threezebra.onesimple.dto;

import java.util.List;

import com.threezebra.restapi.dto.JobTitleJson;

public class AllInfoJson {
	List<UnitDepartmentJson> unitsDepartments;
	List<UserTypeJson> unitUserTypes;
	List<JobRoleJson> jobRoles;
	List<JobTitleJson> jobTitles;
	
	public List<UnitDepartmentJson> getUnitsDepartments() {
		return unitsDepartments;
	}
	public void setUnitsDepartments(List<UnitDepartmentJson> unitsDepartments) {
		this.unitsDepartments = unitsDepartments;
	}
	public List<UserTypeJson> getUnitUserTypes() {
		return unitUserTypes;
	}
	public void setUnitUserTypes(List<UserTypeJson> unitUserTypes) {
		this.unitUserTypes = unitUserTypes;
	}
	public List<JobRoleJson> getJobRoles() {
		return jobRoles;
	}
	public void setJobRoles(List<JobRoleJson> jobRoles) {
		this.jobRoles = jobRoles;
	}
	public List<JobTitleJson> getJobTitles() {
		return jobTitles;
	}
	public void setJobTitles(List<JobTitleJson> jobTitles) {
		this.jobTitles = jobTitles;
	}	
	

}
