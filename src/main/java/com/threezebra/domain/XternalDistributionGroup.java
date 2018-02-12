package com.threezebra.domain;

import io.swagger.annotations.ApiModel;

@ApiModel
public class XternalDistributionGroup {
	
	private long id;
	private String name;
	private String desc;
	private Department department;
	private UserType userTypeObj;
	private String distroActive;
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDistroActive() {
		return distroActive;
	}
	public void setDistroActive(String distroActive) {
		this.distroActive = distroActive;
	}
	public UserType getUserTypeObj() {
		return userTypeObj;
	}
	public void setUserTypeObj(UserType userTypeObj) {
		this.userTypeObj = userTypeObj;
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
