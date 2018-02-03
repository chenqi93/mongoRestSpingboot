package com.threezebra.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModel;


/**
 * The persistent class for the job_role database table.
 * 
 */
@ApiModel
public class JobRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String jobRoleId;
	
	private int createBy;
    
	private Date createDate;

	private int deleted;
	
	private UserType userType;
	
	private String userTypeName;
	
	private List<Unit> unit;
	
	private String checkFlag;
	
	private int modifiedBy;

	private Date modifiedDate;

	private String name;
	
	
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public List<Unit> getUnit() {
		return unit;
	}
	public void setUnit(List<Unit> unit) {
		this.unit = unit;
	}


	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	


	public JobRole() {
	}
	public String getJobRoleId() {
		return jobRoleId;
	}

	public void setJobRoleId(String jobRoleId) {
		this.jobRoleId = jobRoleId;
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
}