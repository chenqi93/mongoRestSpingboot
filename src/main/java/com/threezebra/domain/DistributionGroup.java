package com.threezebra.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the distribution_group database table.
 * 
 */

public class DistributionGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
    private int createBy;
    private Date createDate;
    private int deleted;
    private int modifiedBy;
	private String daily;
    private Date modifiedDate;

	private String name;
	private   Unit  unit;
	private Department department;
	private JobRole  jobRole;

	
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
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
	public DistributionGroup() {
	}
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
   public int getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getDeleted() {
		return this.deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public int getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public String getDaily() {
		return daily;
	}


	public void setDaily(String daily) {
		this.daily = daily;
	}

	

}