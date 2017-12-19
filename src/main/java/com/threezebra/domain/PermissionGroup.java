package com.threezebra.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

public class PermissionGroup {
	@Id
	private long id;
	private String  unit;
	private String department;
	private String jobRole;
	private String name;
	private List<Application> application;
	private DeptDocPrivilege deptDocPrivilege;
	private SharedDocPrivilege  sharedDocPrivilege;
	private OtherDocPrivilege  otherDocPrivilege;

	public OtherDocPrivilege getOtherDocPrivilege() {
		return otherDocPrivilege;
	}
	public void setOtherDocPrivilege(OtherDocPrivilege otherDocPrivilege) {
		this.otherDocPrivilege = otherDocPrivilege;
	}
	public SharedDocPrivilege getSharedDocPrivilege() {
		return sharedDocPrivilege;
	}
	public void setSharedDocPrivilege(SharedDocPrivilege sharedDocPrivilege) {
		this.sharedDocPrivilege = sharedDocPrivilege;
	}
	public DeptDocPrivilege getDeptDocPrivilege() {
		return deptDocPrivilege;
	}
	public void setDeptDocPrivilege(DeptDocPrivilege deptDocPrivilege) {
		this.deptDocPrivilege = deptDocPrivilege;
	}
	public List<Application> getApplication() {
		return application;
	}
	public void setApplication(List<Application> application) {
		this.application = application;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getJobRole() {
		return jobRole;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}
	
	

}
