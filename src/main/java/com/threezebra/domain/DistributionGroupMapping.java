package com.threezebra.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModel;


/**
 * The persistent class for the group_mapping database table.
 * 
 */
@ApiModel
public class DistributionGroupMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;	
	private List<Department> department;
	private DistributionGroup distributionGroup;
	private List<JobRole> jobrole;
	private List<Unit> unit;
	private List<UserType> usertype;

	
	public DistributionGroup getDistributionGroup() {
		return distributionGroup;
	}

	public void setDistributionGroup(DistributionGroup distributionGroup) {
		this.distributionGroup = distributionGroup;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public List<Department> getDepartment() {
		return department;
	}

	public void setDepartment(List<Department> department) {
		this.department = department;
	}

	public List<JobRole> getJobrole() {
		return jobrole;
	}

	public void setJobrole(List<JobRole> jobrole) {
		this.jobrole = jobrole;
	}

	public List<Unit> getUnit() {
		return unit;
	}

	public void setUnit(List<Unit> unit) {
		this.unit = unit;
	}

	public List<UserType> getUsertype() {
		return usertype;
	}

	public void setUsertype(List<UserType> usertype) {
		this.usertype = usertype;
	}
	

}