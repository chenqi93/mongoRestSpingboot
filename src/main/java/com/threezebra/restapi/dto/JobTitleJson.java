package com.threezebra.restapi.dto;

public class JobTitleJson {
	
	public String department;
	public String[] jobTitle;
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String[] getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String[] jobTitle) {
		this.jobTitle = jobTitle;
	}

}
