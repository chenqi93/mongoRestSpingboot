package com.threezebra.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;


/**
 * The persistent class for the emp_details database table.
 * 
 */

public class EmpDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long  id;
	private String accessEndDate;
	private String accessRenwStartDate;
    private String accessStartDate;
    private String accessSusStartDate;
    private List<AdditionalLocation> additionalLocation;
    private BaseLocation baseLocation;
    private String deleted;
	private List<DeviceInfo> deviceIssued;
    private String firstName;
    private String isInvited;
    private JobTitle jobTitle;
    private String lastName;
    private String permittedNumDevices;
    private String personalEmail;
    private String personalPhoneNum;
    private String[] uniqueId;
    private String workEmail;
    private DistributionGroup distributionGroup;
    private DailyDistributionGroup dialyDistributionGroup;
    private Department  department;
    private UserType userType;
    private SpecialRole specialRole;
	private JobRole jobRole;
    private Unit unit;

	public EmpDetail() {
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public DistributionGroup getDistributionGroup() {
		return distributionGroup;
	}

	public void setDistributionGroup(DistributionGroup distributionGroup) {
		this.distributionGroup = distributionGroup;
	}
	

	public DailyDistributionGroup getDialyDistributionGroup() {
		return dialyDistributionGroup;
	}

	public void setDialyDistributionGroup(DailyDistributionGroup dialyDistributionGroup) {
		this.dialyDistributionGroup = dialyDistributionGroup;
	}

	public SpecialRole getSpecialRole() {
		return specialRole;
	}

	public void setSpecialRole(SpecialRole specialRole) {
		this.specialRole = specialRole;
	}

	public String getAccessEndDate() {
		return this.accessEndDate;
	}

	public void setAccessEndDate(String accessEndDate) {
		this.accessEndDate = accessEndDate;
	}

	public String getAccessRenwStartDate() {
		return this.accessRenwStartDate;
	}

	public void setAccessRenwStartDate(String accessRenwStartDate) {
		this.accessRenwStartDate = accessRenwStartDate;
	}

	public String getAccessStartDate() {
		return this.accessStartDate;
	}

	public void setAccessStartDate(String accessStartDate) {
		this.accessStartDate = accessStartDate;
	}

	public String getAccessSusStartDate() {
		return this.accessSusStartDate;
	}

	public void setAccessSusStartDate(String accessSusStartDate) {
		this.accessSusStartDate = accessSusStartDate;
	}

	public List<AdditionalLocation> getAdditionalLocation() {
		return this.additionalLocation;
	}

	public void setAdditionalLocation(List<AdditionalLocation> additionalLocation) {
		this.additionalLocation = additionalLocation;
	}

	public String getDeleted() {
		return this.deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public List<DeviceInfo> getDeviceIssued() {
		return this.deviceIssued;
	}

	public void setDeviceIssued(List<DeviceInfo> deviceIssued) {
		this.deviceIssued = deviceIssued;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getIsInvited() {
		return this.isInvited;
	}

	public void setIsInvited(String  isInvited) {
		this.isInvited = isInvited;
	}

	public BaseLocation getBaseLocation() {
		return baseLocation;
	}

	public void setBaseLocation(BaseLocation baseLocation) {
		this.baseLocation = baseLocation;
	}

	public JobTitle getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPermittedNumDevices() {
		return this.permittedNumDevices;
	}

	public void setPermittedNumDevices(String permittedNumDevices) {
		this.permittedNumDevices = permittedNumDevices;
	}

	public String getPersonalEmail() {
		return this.personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public String getPersonalPhoneNum() {
		return this.personalPhoneNum;
	}

	public void setPersonalPhoneNum(String personalPhoneNum) {
		this.personalPhoneNum = personalPhoneNum;
	}

	public String[] getUniqueId() {
		return this.uniqueId;
	}

	public void setUniqueId(String[] uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getWorkEmail() {
		return this.workEmail;
	}

	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}

	public Department  getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public JobRole getJobRole() {
		return jobRole;
	}

	public void setJobRole(JobRole jobRole) {
		this.jobRole = jobRole;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	
}