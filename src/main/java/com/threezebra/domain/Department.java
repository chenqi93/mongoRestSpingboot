package com.threezebra.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModel;


/**
 * The persistent class for the department database table.
 * 
 */
@ApiModel
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String departmentId;
	private int createBy;
	private String checkFlag;

	
	private Date createDate;

	private int deleted;

	
	private int modifiedBy;

	
	private Date modifiedDate;

	private String  name;
	
    private List<UserType> userType;
    
    private List<Unit> unit;

	public String getCheckFlag() {
		return checkFlag;
	}


	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	private String description;
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}


	public List<UserType> getUserType() {
		return userType;
	}


	public void setUserType(List<UserType> userType) {
		this.userType = userType;
	}


	public List<Unit> getUnit() {
		return unit;
	}


	public void setUnit(List<Unit> unit) {
		this.unit = unit;
	}
   
	
	public Department() {
	
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