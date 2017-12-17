package com.threezebra.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;


/**
 * The persistent class for the special_role database table.
 * 
 */

public class SpecialRole implements Serializable {
	private static final long serialVersionUID = 1L;
  
	@Id
	private long id;

	private int createBy;

	private Date createDate;
	
    private BaseLocation baseLocation;
	private int deleted;

	private int modifiedBy;

	private Date modifiedDate;
	
	private String specialRoleFlag;

	private String name;

	private String specialJobRole;
	
	private String specialJobRoleDesc;

	public String getSpecialJobRoleDesc() {
		return specialJobRoleDesc;
	}
   public void setSpecialJobRoleDesc(String specialJobRoleDesc) {
		this.specialJobRoleDesc = specialJobRoleDesc;
	}
    public String getSpecialJobRole() {
		return specialJobRole;
	}
    public void setSpecialJobRole(String specialJobRole) {
		this.specialJobRole = specialJobRole;
	}
    public SpecialRole() {
	}
    public long getId() {
		return id;
	}
    public void setId(long id) {
		this.id = id;
	}
    
    public BaseLocation getBaseLocation() {
		return baseLocation;
	}
	public void setBaseLocation(BaseLocation baseLocation) {
		this.baseLocation = baseLocation;
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
	public String getSpecialRoleFlag() {
		return specialRoleFlag;
	}
	public void setSpecialRoleFlag(String specialRoleFlag) {
		this.specialRoleFlag = specialRoleFlag;
	}
}