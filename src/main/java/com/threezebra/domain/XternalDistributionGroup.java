package com.threezebra.domain;

import io.swagger.annotations.ApiModel;

@ApiModel
public class XternalDistributionGroup {
	
	private long id;
	private String name;
	private Department department;
	private UserType userType;
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	private JobRole jobRole;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public JobRole getJobRole() {
		return jobRole;
	}
	public void setJobRole(JobRole jobRole) {
		this.jobRole = jobRole;
	}

}
