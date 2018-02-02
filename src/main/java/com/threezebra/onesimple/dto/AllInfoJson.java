package com.threezebra.onesimple.dto;

import java.util.List;

import com.threezebra.restapi.dto.JobTitleJson;

public class AllInfoJson {
	List<UnitDepartmentJson> unitsDepartments;
	List<UserTypeJson> unitUserTypes;
	List<JobRoleJson> jobRoles;
	List<JobTitleJson> jobTitles;
	ApplicationJson applications;
	DeptDocPrivilegeJson deptDocPrivilegeJson;
	SpecialJobRoleJson specialJobRoleJson;
	SharedDocPrivilegeJson sharedDocPrivilegeJson;
	List<XternalListJson> xternalListJson;
	AdditionalLocationJson additionalLocationJson;
	OtherDocPreveligesJson otherDocPreveligesJson;
	public ApplicationJson getApplications() {
		return applications;
	}
	public void setApplications(ApplicationJson applications) {
		this.applications = applications;
	}
	public DeptDocPrivilegeJson getDeptDocPrivilegeJson() {
		return deptDocPrivilegeJson;
	}
	public void setDeptDocPrivilegeJson(DeptDocPrivilegeJson deptDocPrivilegeJson) {
		this.deptDocPrivilegeJson = deptDocPrivilegeJson;
	}
	public SpecialJobRoleJson getSpecialJobRoleJson() {
		return specialJobRoleJson;
	}
	public void setSpecialJobRoleJson(SpecialJobRoleJson specialJobRoleJson) {
		this.specialJobRoleJson = specialJobRoleJson;
	}
	public SharedDocPrivilegeJson getSharedDocPrivilegeJson() {
		return sharedDocPrivilegeJson;
	}
	public void setSharedDocPrivilegeJson(SharedDocPrivilegeJson sharedDocPrivilegeJson) {
		this.sharedDocPrivilegeJson = sharedDocPrivilegeJson;
	}
	public List<XternalListJson> getXternalListJson() {
		return xternalListJson;
	}
	public void setXternalListJson(List<XternalListJson> xternalListJson) {
		this.xternalListJson = xternalListJson;
	}
	public AdditionalLocationJson getAdditionalLocationJson() {
		return additionalLocationJson;
	}
	public void setAdditionalLocationJson(AdditionalLocationJson additionalLocationJson) {
		this.additionalLocationJson = additionalLocationJson;
	}
	public OtherDocPreveligesJson getOtherDocPreveligesJson() {
		return otherDocPreveligesJson;
	}
	public void setOtherDocPreveligesJson(OtherDocPreveligesJson otherDocPreveligesJson) {
		this.otherDocPreveligesJson = otherDocPreveligesJson;
	}
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
