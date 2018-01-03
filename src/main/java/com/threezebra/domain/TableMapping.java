package com.threezebra.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModel;


/**
 * The persistent class for the table_mapping database table.
 * 
 */
@ApiModel
public class TableMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String shortCode;

	private String name;

	public TableMapping() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShortCode() {
		return this.shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}