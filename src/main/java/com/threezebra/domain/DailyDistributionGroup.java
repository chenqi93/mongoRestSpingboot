package com.threezebra.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModel;
@ApiModel
public class DailyDistributionGroup {
	@Id
	private long id;
    private int createBy;
    private Date createDate;
    private int deleted;
    private int modifiedBy;
    private Date modifiedDate;
    private String defaultvalue;
	private String name;
	private  Unit  unit;
	private  List<String> location;
	private Department department;
	private List<Unit> unitlist;
	private List<Department> deptlist;
	private List<UserType> userType;
	
	public DailyDistributionGroup() {
	}
	
	public List<String> getLocation() {
		return location;
	}
	public void setLocation(List<String> location) {
		this.location = location;
	}
	public String getDefaultvalue() {
		return defaultvalue;
	}
	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}
	public List<Unit> getUnitlist() {
		return unitlist;
	}
	public void setUnitlist(List<Unit> unitlist) {
		this.unitlist = unitlist;
	}
	public List<Department> getDeptlist() {
		return deptlist;
	}
	public void setDeptlist(List<Department> deptlist) {
		this.deptlist = deptlist;
	}
	public List<UserType> getUserType() {
		return userType;
	}
	public void setUserType(List<UserType> userType) {
		this.userType = userType;
	}


	private List<JobRole>  jobRole;

	
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
	public List<JobRole> getJobRole() {
		return jobRole;
	}
	public void setJobRole(List<JobRole> jobRole) {
		this.jobRole = jobRole;
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
}
