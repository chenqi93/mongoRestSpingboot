package com.threezebra.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

public class  PermissionGroupMapping {

	@Id
	private long id;
	private String name;
	private BaseLocation location;
	private PermissionGroup permissionGroup;
	private List<Application> applications;
	private DeptDocPrivilege deptDocPrivileges;
	private SharedDocPrivilege sharedDocPrivilege;
	private OtherDocPrivilege otherDocPrivilege;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public BaseLocation getLocation() {
		return location;
	}
	public void setLocation(BaseLocation location) {
		this.location = location;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PermissionGroup getPermissionGroup() {
		return permissionGroup;
	}
	public void setPermissionGroup(PermissionGroup permissionGroup) {
		this.permissionGroup = permissionGroup;
	}
	
	public List<Application> getApplications() {
		return applications;
	}
	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	public DeptDocPrivilege getDeptDocPrivileges() {
		return deptDocPrivileges;
	}
	public void setDeptDocPrivileges(DeptDocPrivilege deptDocPrivileges) {
		this.deptDocPrivileges = deptDocPrivileges;
	}
	public SharedDocPrivilege getSharedDocPrivilege() {
		return sharedDocPrivilege;
	}
	public void setSharedDocPrivilege(SharedDocPrivilege sharedDocPrivilege) {
		this.sharedDocPrivilege = sharedDocPrivilege;
	}
	public OtherDocPrivilege getOtherDocPrivilege() {
		return otherDocPrivilege;
	}
	public void setOtherDocPrivilege(OtherDocPrivilege otherDocPrivilege) {
		this.otherDocPrivilege = otherDocPrivilege;
	}
	
	
}
