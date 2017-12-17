package com.threezebra.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the units database table.
 * 
 */

public class Unit implements Serializable {
	


	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String  createBy;

	private Date createDate;

	private int deleted;

	private int modifiedBy;

	private Date modifiedDate;
	
	private String description;
	
    private BaseLocation baselocation;
	
    private String unitId;
    

	private String name;
    
    private List<Department> department;

    public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

    public String getUnitId() {
		return unitId;
	}


	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}


	public List<Department> getDepartment() {
		return department;
	}


	public void setDepartment(List<Department> department) {
		this.department = department;
	}


	public Unit() {
	}



	

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
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


	public BaseLocation getBaselocation() {
		return baselocation;
	}


	public void setBaselocation(BaseLocation baselocation) {
		this.baselocation = baselocation;
	}


	

	

}