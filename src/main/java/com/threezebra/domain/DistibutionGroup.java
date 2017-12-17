package com.threezebra.domain;

import org.springframework.data.annotation.Id;

public class DistibutionGroup {
	
	@Id
	private long id;
	
	private String country;
	private String location;
	private String group;
	private String[] applications;
	private String deptDocPrivileges;
	private String sahredDocPrivileges;
	private String otherPrivileges;
	public String getCountry() {
		return country;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String[] getApplications() {
		return applications;
	}
	public void setApplications(String[] applications) {
		this.applications = applications;
	}
	public String getDeptDocPrivileges() {
		return deptDocPrivileges;
	}
	public void setDeptDocPrivileges(String deptDocPrivileges) {
		this.deptDocPrivileges = deptDocPrivileges;
	}
	public String getSahredDocPrivileges() {
		return sahredDocPrivileges;
	}
	public void setSahredDocPrivileges(String sahredDocPrivileges) {
		this.sahredDocPrivileges = sahredDocPrivileges;
	}
	public String getOtherPrivileges() {
		return otherPrivileges;
	}
	public void setOtherPrivileges(String otherPrivileges) {
		this.otherPrivileges = otherPrivileges;
	}
}
