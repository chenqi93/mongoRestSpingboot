package com.threezebra.domain;

import java.util.List;

public class DistributionList {
	
    String distroname;
	String  unit;
	String deparment;
	String usertype;
	List<String> jobRole;
	public String getDistroname() {
		return distroname;
	}
	public void setDistroname(String distroname) {
		this.distroname = distroname;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDeparment() {
		return deparment;
	}
	public void setDeparment(String deparment) {
		this.deparment = deparment;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public List<String> getJobRole() {
		return jobRole;
	}
	public void setJobRole(List<String> jobRole) {
		this.jobRole = jobRole;
	}

}
