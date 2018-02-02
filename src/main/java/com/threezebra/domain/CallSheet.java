package com.threezebra.domain;

import java.util.List;

public class CallSheet {

	long id;
	String name;
	List<String> location;
	Unit unit;
	List<Department> department;
	List<UserType> userType;
	String defaultvalue;
	List<JobRole> jobRole;
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
	public List<String> getLocation() {
		return location;
	}
	public void setLocation(List<String> location) {
		this.location = location;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	public List<Department> getDepartment() {
		return department;
	}
	public void setDepartment(List<Department> department) {
		this.department = department;
	}
	public List<UserType> getUserType() {
		return userType;
	}
	public void setUserType(List<UserType> userType) {
		this.userType = userType;
	}
	public String getDefaultvalue() {
		return defaultvalue;
	}
	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}
	public List<JobRole> getJobRole() {
		return jobRole;
	}
	public void setJobRole(List<JobRole> jobRole) {
		this.jobRole = jobRole;
	}
	
}
