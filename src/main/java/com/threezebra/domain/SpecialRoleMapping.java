package com.threezebra.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModel;
@ApiModel
public class SpecialRoleMapping {
	
	@Id
	private long id;
	private SpecialRole specialRole;
	private List<Application> application;
	private String specialPermissionGroup;
	private List<PermissionGroup> permissionGroup;
	private String egnyteFolder;
	private String specialPrivilege;
	
	public String getSpecialPermissionGroup() {
		return specialPermissionGroup;
	}
	public void setSpecialPermissionGroup(String specialPermissionGroup) {
		this.specialPermissionGroup = specialPermissionGroup;
	}
	public SpecialRole getSpecialRole() {
		return specialRole;
	}
	public void setSpecialRole(SpecialRole specialRole) {
		this.specialRole = specialRole;
	}
	public List<Application> getApplication() {
		return application;
	}
	public void setApplication(List<Application> application) {
		this.application = application;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEgnyteFolder() {
		return egnyteFolder;
	}
	public void setEgnyteFolder(String egnyteFolder) {
		this.egnyteFolder = egnyteFolder;
	}
	public String getSpecialPrivilege() {
		return specialPrivilege;
	}
	public void setSpecialPrivilege(String specialPrivilege) {
		this.specialPrivilege = specialPrivilege;
	}
	public List<PermissionGroup> getPermissionGroup() {
		return permissionGroup;
	}
	public void setPermissionGroup(List<PermissionGroup> permissionGroup) {
		this.permissionGroup = permissionGroup;
	}
	
	

}
