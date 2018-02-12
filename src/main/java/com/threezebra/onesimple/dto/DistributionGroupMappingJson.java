package com.threezebra.onesimple.dto;

public class DistributionGroupMappingJson {
	
	private String[] location;
	private long[] department;
	private String distributionGroup;
	private long[] jobrole;
	private long[] unit;
	private String defaultvalue;
	private long[] usertype; 
	private String employeeListflag; 
	private   String isActive;
	
	
	public String getEmployeeListflag() {
		return employeeListflag;
	}
	public void setEmployeeListflag(String employeeListflag) {
		this.employeeListflag = employeeListflag;
	}
	public String[] getLocation() {
		return location;
	}
	public void setLocation(String[] location) {
		this.location = location;
	}
	public long[] getDepartment() {
		return department;
	}
	public void setDepartment(long[] department) {
		this.department = department;
	}
	public String getDistributionGroup() {
		return distributionGroup;
	}
	public void setDistributionGroup(String distributionGroup) {
		this.distributionGroup = distributionGroup;
	}
	public long[] getJobrole() {
		return jobrole;
	}
	public void setJobrole(long[] jobrole) {
		this.jobrole = jobrole;
	}
	public long[] getUnit() {
		return unit;
	}
	public void setUnit(long[] unit) {
		this.unit = unit;
	}
	public long[] getUsertype() {
		return usertype;
	}
	public void setUsertype(long[] usertype) {
		this.usertype = usertype;
	}
	public String getDefaultvalue() {
		return defaultvalue;
	}
	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
}
