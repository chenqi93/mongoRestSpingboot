package com.threezebra.onesimple.dto;

public class UserTypeJson {
	private String[] unit;
	private String[] usertype;
	private String[] usertypeDesc;
	
	
	public String[] getUsertypeDesc() {
		return usertypeDesc;
	}
	public void setUsertypeDesc(String[] usertypeDesc) {
		this.usertypeDesc = usertypeDesc;
	}
	public String[] getUnit() {
		return unit;
	}
	public void setUnit(String[] unit) {
		this.unit = unit;
	}
	public String[] getUsertype() {
		return usertype;
	}
	public void setUsertype(String[] usertype) {
		this.usertype = usertype;
	}
	
}
