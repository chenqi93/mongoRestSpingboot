package com.threezebra.onesimple.dto;

public class SpecialJobRoleJson {
	private String baseLocation;
	private String[] specialJobRole;
	private String[] roleDescription;
	public String getBaseLocation() {
		return baseLocation;
	}
	public void setBaseLocation(String baseLocation) {
		this.baseLocation = baseLocation;
	}
	public String[] getSpecialJobRole() {
		return specialJobRole;
	}
	public void setSpecialJobRole(String[] specialJobRole) {
		this.specialJobRole = specialJobRole;
	}
	public String[] getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String[] roleDescription) {
		this.roleDescription = roleDescription;
	}

}
