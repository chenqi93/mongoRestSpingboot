package com.threezebra.onesimple.dto;

public class SpecialRoleMappingJson {
	private long specialRoleId;
	private String specialRoleName;
	private String specialRoleDesc;
	private long[] application;
	private String emplistFlag;
	private String specialPermissionGroup;
	private String egnytePath;
	private long[] PermissionGroup;
	private String specialPrivileges;
	
	
	
   public long[] getPermissionGroup() {
		return PermissionGroup;
	}
	public void setPermissionGroup(long[] permissionGroup) {
		PermissionGroup = permissionGroup;
	}
public long getSpecialRoleId() {
		return specialRoleId;
	}
	public void setSpecialRoleId(long specialRoleId) {
		this.specialRoleId = specialRoleId;
	}
	public long[] getApplication() {
		return application;
	}
	public void setApplication(long[] application) {
		this.application = application;
	}
	public String getSpecialPermissionGroup() {
		return specialPermissionGroup;
	}
	public void setSpecialPermissionGroup(String specialPermissionGroup) {
		this.specialPermissionGroup = specialPermissionGroup;
	}
	public String getEgnytePath() {
		return egnytePath;
	}
	public void setEgnytePath(String egnytePath) {
		this.egnytePath = egnytePath;
	}
	public String getSpecialPrivileges() {
		return specialPrivileges;
	}
	public void setSpecialPrivileges(String specialPrivileges) {
		this.specialPrivileges = specialPrivileges;
	}
	public String getEmplistFlag() {
		return emplistFlag;
	}
	public void setEmplistFlag(String emplistFlag) {
		this.emplistFlag = emplistFlag;
	}
	public String getSpecialRoleName() {
		return specialRoleName;
	}
	public void setSpecialRoleName(String specialRoleName) {
		this.specialRoleName = specialRoleName;
	}
	public String getSpecialRoleDesc() {
		return specialRoleDesc;
	}
	public void setSpecialRoleDesc(String specialRoleDesc) {
		this.specialRoleDesc = specialRoleDesc;
	}
	

}
