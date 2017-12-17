package com.threezebra.onesimple.dto;

public class PermissionGroupMappingJson {
	
    private String location;
	private String permissiongroupname;
	private long[] applications;
	private long deptDocPrivilege;
	private long sharedDocPrivilege;
	private long otherDocPrivilege;
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPermissiongroupname() {
		return permissiongroupname;
	}
	public void setPermissiongroupname(String permissiongroupname) {
		this.permissiongroupname = permissiongroupname;
	}
	
	public long[] getApplications() {
		return applications;
	}
	public void setApplications(long[] applications) {
		this.applications = applications;
	}
	public long getDeptDocPrivilege() {
		return deptDocPrivilege;
	}
	public void setDeptDocPrivilege(long deptDocPrivilege) {
		this.deptDocPrivilege = deptDocPrivilege;
	}
	public long getSharedDocPrivilege() {
		return sharedDocPrivilege;
	}
	public void setSharedDocPrivilege(long sharedDocPrivilege) {
		this.sharedDocPrivilege = sharedDocPrivilege;
	}
	public long getOtherDocPrivilege() {
		return otherDocPrivilege;
	}
	public void setOtherDocPrivilege(long otherDocPrivilege) {
		this.otherDocPrivilege = otherDocPrivilege;
	}
	
	
}
