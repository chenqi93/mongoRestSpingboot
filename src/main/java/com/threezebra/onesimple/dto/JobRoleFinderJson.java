package com.threezebra.onesimple.dto;

import java.util.List;

import com.threezebra.domain.Unit;
import com.threezebra.domain.UserType;

public class JobRoleFinderJson {
	List<UserType> userTypeList;
	Unit unit;
	public List<UserType> getUserTypeList() {
		return userTypeList;
	}
	public void setUserTypeList(List<UserType> userTypeList) {
		this.userTypeList = userTypeList;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}

}
