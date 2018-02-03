package com.threezebra.onesimple.dto;

import java.util.List;

import com.threezebra.domain.AdditionalLocation;
import com.threezebra.domain.BaseLocation;

public class FindLocationJson {
	private String distributionGroup;
	private String defaultvalue;
	private String employeeListflag;
	private String isActive;
	
	
	
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getEmployeeListflag() {
		return employeeListflag;
	}
	public void setEmployeeListflag(String employeeListflag) {
		this.employeeListflag = employeeListflag;
	}
	List<AdditionalLocation> additionalLocation;
	BaseLocation baseLocation;
	public List<AdditionalLocation> getAdditionalLocation() {
		return additionalLocation;
	}
	public void setAdditionalLocation(List<AdditionalLocation> additionalLocation) {
		this.additionalLocation = additionalLocation;
	}
	public BaseLocation getBaseLocation() {
		return baseLocation;
	}
	public void setBaseLocation(BaseLocation baseLocation) {
		this.baseLocation = baseLocation;
	}
	public String getDistributionGroup() {
		return distributionGroup;
	}
	public void setDistributionGroup(String distributionGroup) {
		this.distributionGroup = distributionGroup;
	}
	public String getDefaultvalue() {
		return defaultvalue;
	}
	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

}
