package com.threezebra.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the base_location database table.
 * 
 */

public class BaseLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;


	private int createBy;

	
	private Date createDate;

	private int deleted;

	
	private int modifiedBy;

	
	private Date modifiedDate;

	private String name;

	//bi-directional many-to-one association to AdditionalLocation
	
	private List<AdditionalLocation> additionalLocations;

	public BaseLocation() {
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

	

	public List<AdditionalLocation> getAdditionalLocations() {
		return this.additionalLocations;
	}

	public void setAdditionalLocations(List<AdditionalLocation> additionalLocations) {
		this.additionalLocations = additionalLocations;
	}

	public AdditionalLocation addAdditionalLocation(AdditionalLocation additionalLocation) {
		getAdditionalLocations().add(additionalLocation);
		additionalLocation.setBaseLocation(this);

		return additionalLocation;
	}

	public AdditionalLocation removeAdditionalLocation(AdditionalLocation additionalLocation) {
		getAdditionalLocations().remove(additionalLocation);
		additionalLocation.setBaseLocation(null);

		return additionalLocation;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}

}