package com.threezebra.onesimple.dto;

public class EmployeeJson {
	
	private long id;
	private String accessEndDate;
	private String accessRenwStartDate;
	private String accessStartDate;
    private String accessSusStartDate;
    private long[] additionalLocation;
    private long baseLocation;
    private String deleted;
    private long[] deviceIssued;
    private String firstName;
    private String lastName;
    private String isInvited;
    private long jobTitle;
    private String permittedNumDevices;
    private String  personalEmail;
    private String  personalPhoneNum;
    private String[] uniqueId;
    private String workEmail;
    private long department;
    private long userType;
    private long specialRole;
    private long jobRole;
    private long unit;
    private String saveFlag;
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccessEndDate() {
		return accessEndDate;
	}
	
	public void setAccessEndDate(String accessEndDate) {
		this.accessEndDate = accessEndDate;
	}
	public String getAccessRenwStartDate() {
		return accessRenwStartDate;
	}
	public void setAccessRenwStartDate(String accessRenwStartDate) {
		this.accessRenwStartDate = accessRenwStartDate;
	}
	public String getAccessStartDate() {
		return accessStartDate;
	}
	public void setAccessStartDate(String accessStartDate) {
		this.accessStartDate = accessStartDate;
	}
	public String getAccessSusStartDate() {
		return accessSusStartDate;
	}
	public void setAccessSusStartDate(String accessSusStartDate) {
		this.accessSusStartDate = accessSusStartDate;
	}
	
	public String getDeleted() {
		return deleted;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getIsInvited() {
		return isInvited;
	}
	public void setIsInvited(String isInvited) {
		this.isInvited = isInvited;
	}
	public long getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(long jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getPermittedNumDevices() {
		return permittedNumDevices;
	}
	public void setPermittedNumDevices(String permittedNumDevices) {
		this.permittedNumDevices = permittedNumDevices;
	}
	public String getPersonalEmail() {
		return personalEmail;
	}
	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}
	public String getPersonalPhoneNum() {
		return personalPhoneNum;
	}
	public void setPersonalPhoneNum(String personalPhoneNum) {
		this.personalPhoneNum = personalPhoneNum;
	}

	public long[] getAdditionalLocation() {
		return additionalLocation;
	}

	public void setAdditionalLocation(long[] additionalLocation) {
		this.additionalLocation = additionalLocation;
	}

	

	public long getBaseLocation() {
		return baseLocation;
	}

	public void setBaseLocation(long baseLocation) {
		this.baseLocation = baseLocation;
	}

	public long[] getDeviceIssued() {
		return deviceIssued;
	}

	public void setDeviceIssued(long[] deviceIssued) {
		this.deviceIssued = deviceIssued;
	}

	
	public String[] getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String[] uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getWorkEmail() {
		return workEmail;
	}

	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}

	public long getDepartment() {
		return department;
	}

	public void setDepartment(long department) {
		this.department = department;
	}

	public long getUserType() {
		return userType;
	}

	public void setUserType(long userType) {
		this.userType = userType;
	}

	public long getSpecialRole() {
		return specialRole;
	}

	public void setSpecialRole(long specialRole) {
		this.specialRole = specialRole;
	}

	public long getJobRole() {
		return jobRole;
	}

	public void setJobRole(long jobRole) {
		this.jobRole = jobRole;
	}

	public long getUnit() {
		return unit;
	}

	public void setUnit(long unit) {
		this.unit = unit;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getSaveFlag() {
		return saveFlag;
	}

	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}
	
	
}
