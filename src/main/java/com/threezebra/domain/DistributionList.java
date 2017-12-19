package com.threezebra.domain;

import java.util.List;

public class DistributionList {
	
    DistributionGroup distroname;
	Unit  unit;
	Department deparment;
	UserType usertype;
	List<JobRole> jobRole;
	
	
	public DistributionGroup getDistroname() {
		return distroname;
	}
	public void setDistroname(DistributionGroup distroname) {
		this.distroname = distroname;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	public Department getDeparment() {
		return deparment;
	}
	public void setDeparment(Department deparment) {
		this.deparment = deparment;
	}
	public UserType getUsertype() {
		return usertype;
	}
	public void setUsertype(UserType usertype) {
		this.usertype = usertype;
	}
	public List<JobRole> getJobRole() {
		return jobRole;
	}
	public void setJobRole(List<JobRole> jobRole) {
		this.jobRole = jobRole;
	}
	

}
