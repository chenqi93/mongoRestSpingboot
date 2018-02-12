package com.threezebra.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModel;

/**
 * The persistent class for the user_types database table.
 * 
 */
@ApiModel
public class UserType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	private int createBy;
	private String checkFlag;
	

	private Date createDate;
	private int deleted;
	private int modifiedBy;
	private Date modifiedDate;
	private List<Unit> unit;
	private String name;
	private String desc;
	
    private List<JobRole> jobRoleList;
    
    
    
    public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	
	
	public String getName() {
		return name;
	}

	public List<JobRole> getJobRoleList() {
		return jobRoleList;
	}

	public void setJobRoleList(List<JobRole> jobRoleList) {
		this.jobRoleList = jobRoleList;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Unit> getUnit() {
		return unit;
	}

	public void setUnit(List<Unit> unit) {
		this.unit = unit;
	}

	public UserType() {
	}


	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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