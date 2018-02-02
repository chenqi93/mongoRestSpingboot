package com.threezebra.onesimple.dto;

import java.util.List;

import com.threezebra.domain.Department;
import com.threezebra.domain.Unit;
import com.threezebra.domain.UserType;

public class UnitDepartmentObjectJson {
	
	Unit unit;
	List<Department> department;
	List<UserType> usertype;
	public List<UserType> getUsertype() {
		return usertype;
	}
	public void setUsertype(List<UserType> usertype) {
		this.usertype = usertype;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	public List<Department> getDepartment() {
		return department;
	}
	public void setDepartment(List<Department> department) {
		this.department = department;
	}

}
