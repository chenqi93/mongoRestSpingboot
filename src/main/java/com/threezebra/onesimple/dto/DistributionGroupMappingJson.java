package com.threezebra.onesimple.dto;

public class DistributionGroupMappingJson {
	
	private long[] department;
	private String distributionGroup;
	private  long[] jobrole;
	private long[] unit;
	private  long usertype;
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
	public long getUsertype() {
		return usertype;
	}
	public void setUsertype(long usertype) {
		this.usertype = usertype;
	}
	
}
