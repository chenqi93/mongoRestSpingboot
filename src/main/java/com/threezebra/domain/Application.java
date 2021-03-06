package com.threezebra.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModel;


/**
 * The persistent class for the applications database table.
 * 
 */
@ApiModel
public class Application implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	
	private int createBy;

	
	private Date createDate;

	private int deleted;

	
	private int modifiedBy;

	
	private Date modifiedDate;

	private String name;

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Application() {
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

	

}