package com.threezebra.onesimple.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.threezebra.domain.AdditionalLocation;
import com.threezebra.domain.Application;
import com.threezebra.domain.BaseResponse;
import com.threezebra.domain.Department;
import com.threezebra.domain.DeptDocPrivilege;
import com.threezebra.domain.DeviceInfo;
import com.threezebra.domain.DistributionGroup;
import com.threezebra.domain.DistributionGroupMapping;
import com.threezebra.domain.DistributionList;
import com.threezebra.domain.EmpDetail;
import com.threezebra.domain.JobRole;
import com.threezebra.domain.JobTitle;
import com.threezebra.domain.OtherDocPrivilege;
import com.threezebra.domain.PermissionGroup;
import com.threezebra.domain.PermissionGroupMapping;
import com.threezebra.domain.SharedDocPrivilege;
import com.threezebra.domain.SpecialRole;
import com.threezebra.domain.SpecialRoleMapping;
import com.threezebra.domain.Unit;
import com.threezebra.domain.UserType;
import com.threezebra.domain.XternalDistributionGroup;
import com.threezebra.onesimple.dto.EmployeeJson;
import com.threezebra.restapi.ValidatingUserRepositoryDecorator;
import com.threezebra.service.AdditionalLocationService;
import com.threezebra.service.ApplicationService;
import com.threezebra.service.BaseLocationService;
import com.threezebra.service.DepartmentService;
import com.threezebra.service.DeptDocPrivilegeService;
import com.threezebra.service.DeviceInfoService;
import com.threezebra.service.DistributionGroupAssignmentService;
import com.threezebra.service.DistributionGroupService;
import com.threezebra.service.EmployeeService;
import com.threezebra.service.JobRoleService;
import com.threezebra.service.JobTitleService;
import com.threezebra.service.OtherDocPrivilegeService;
import com.threezebra.service.PermissionGroupMappingService;
import com.threezebra.service.PermissionGroupService;
import com.threezebra.service.SharedDocPrivilegeService;
import com.threezebra.service.SpecialRoleMappingService;
import com.threezebra.service.SpecialRoleService;
import com.threezebra.service.UnitService;
import com.threezebra.service.UserTypeService;
import com.threezebra.service.XterlDistributionGroupService;

@RestController
public class EmpDetailController {

	@Autowired

	public static final String OWNER = "authentication.name == #userName";
	public static final String ADMIN = "hasRole('ADMIN')";
	public static final String USER = "hasRole('USER')";
	@Autowired
	BaseLocationService baseLocationService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	UnitService unitService;
	@Autowired
	UserTypeService userTypeService;
	@Autowired
	JobRoleService jobRoleService;
	@Autowired
	EmployeeService empDetailService;
	@Autowired
	ApplicationService applicationService;
	@Autowired
	SpecialRoleService specialRoleService;
	@Autowired
	DistributionGroupAssignmentService distriGroupAssignmentService;
	@Autowired
	DistributionGroupService distributionGroupService;
	@Autowired
	DistributionGroupService dailyDistributionService;
	@Autowired
	PermissionGroupService permissionGroupService;
	@Autowired
	JobTitleService jobTitleService;
	
	@Autowired
	SharedDocPrivilegeService sharedDocPrivilegeService;
	@Autowired
	AdditionalLocationService additionalLocationService;
	
	@Autowired
	DeviceInfoService deviceInfoService;
	@Autowired
	DeptDocPrivilegeService deptDocPrivilegeService;
	@Autowired
	OtherDocPrivilegeService otherDocPrivilegeService;
	@Autowired
	PermissionGroupMappingService permissionGroupMappingService;
	@Autowired
	SpecialRoleMappingService specialRoleMappingService;
	@Autowired
	XterlDistributionGroupService xterlDistributionGroupService;

	@Autowired
	private ValidatingUserRepositoryDecorator validatingUserRepositoryDecorator;

	//@PreAuthorize(USER)
	@RequestMapping(value = "/getEmpDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmpDetail>> listAllEmpDetails() {
		//System.out.println("username::::::" + userName);
		//validatingUserRepositoryDecorator.findAccountValidated(userName);

		List<EmpDetail> EmpDetailList = empDetailService.findAll();
		return new ResponseEntity<List<EmpDetail>>(EmpDetailList, HttpStatus.OK);
	}

	@RequestMapping(value = "/createEmpDetail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity saveEmpDetailInfo(@RequestBody EmployeeJson employeeJson) {
		EmpDetail empDetails=new EmpDetail();
		empDetails.setId(System.nanoTime());
		empDetails.setFirstName(employeeJson.getFirstName());
		empDetails.setLastName(employeeJson.getLastName());
		empDetails.setAccessEndDate(employeeJson.getAccessEndDate());
		empDetails.setAccessRenwStartDate(employeeJson.getAccessRenwStartDate());
		empDetails.setAccessStartDate(employeeJson.getAccessStartDate());
		empDetails.setAccessSusStartDate(employeeJson.getAccessSusStartDate());
		empDetails.setBaseLocation(baseLocationService.findByName(employeeJson.getBaseLocation()));
		empDetails.setUnit(unitService.findbyId(employeeJson.getUnit()));
		long [] deptarr=employeeJson.getDepartment();
		for(int i=0;i<deptarr.length;i++) {
			Department dept=departmentService.findById(deptarr[i]);
			List<Department> deptlist=new ArrayList<>();
	        deptlist.add(dept);
			empDetails.setDepartment(deptlist);
			
		}
		long [] additionalarr=employeeJson.getAdditionalLocation();
		for(int i=0;i<additionalarr.length;i++) {
			AdditionalLocation addloc=additionalLocationService.findByName(additionalarr[i]);
	        List<AdditionalLocation> addloclist=new ArrayList<>();
			addloclist.add(addloc);
			empDetails.setAdditionalLocation(addloclist);
		}
		long [] deviceIssuedarr=employeeJson.getAdditionalLocation();
		 List<DeviceInfo> deviceIssued=new ArrayList<>();
		for(int i=0;i<deviceIssuedarr.length;i++) {
			DeviceInfo deviceInfo=deviceInfoService.findByName(deviceIssuedarr[i]);
			deviceIssued.add(deviceInfo);
		 	}
		
		empDetails.setDeviceIssued(deviceIssued);
		UserType userType=userTypeService.findById(employeeJson.getUserType());
		SpecialRole specialRole=specialRoleService.findByName(employeeJson.getSpecialRole());
		empDetails.setSpecialRole(specialRole);
		empDetails.setUserType(userType);
		empDetails.setDeleted(employeeJson.getDeleted());
		empDetails.setIsInvited(employeeJson.getIsInvited());
		JobRole jobRole =jobRoleService.findById(employeeJson.getJobRole());
	    empDetails.setJobRole(jobRole);
	    JobTitle jobTitle=jobTitleService.findByName(employeeJson.getJobTitle());
	    empDetails.setJobTitle(jobTitle);
	    empDetails.setPermittedNumDevices(employeeJson.getPermittedNumDevices());
	    empDetails.setPersonalEmail(employeeJson.getPersonalEmail());
	    empDetails.setPersonalPhoneNum(employeeJson.getPersonalPhoneNum());
	    empDetails.setUniqueId(employeeJson.getUniqueId());
	    empDetails.setWorkEmail(employeeJson.getWorkEmail());
		empDetailService.save(empDetails);
	     BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/unit/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Unit>> getUnits() {
		List<Unit> unitList = unitService.findAll();
		return new ResponseEntity<>(unitList, HttpStatus.OK);
	}

	@RequestMapping(value = "/department/",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Department>> getDepartment(){
		List<Department> deptList = departmentService.findAll();
		return new ResponseEntity<>(deptList,HttpStatus.OK);
	}

	@RequestMapping(value = "/department/{unitName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Department>> getDepartmentbyUnit(@PathVariable String unitName) {
		long id=Long.parseLong(unitName);
		Unit unit=unitService.findbyId(id);
		List<Department> deptList = departmentService.findByUnit(unit);
		return new ResponseEntity<>(deptList, HttpStatus.OK);
	}

	@RequestMapping(value = "/usertype/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserType>> getUserType() {
		List<UserType> userTypesList = userTypeService.findAll();
		return new ResponseEntity<>(userTypesList, HttpStatus.OK);
	}

	@RequestMapping(value = "/jobrole/{userTypeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JobRole>> getJobRoles(@PathVariable String userTypeId) { 
		long id=Long.parseLong(userTypeId);
		UserType userType=userTypeService.findById(id);
		List<JobRole> jobRoleList = jobRoleService.findByUserType(userType);
		return new ResponseEntity<>(jobRoleList, HttpStatus.OK);
	}

	@RequestMapping(value = "/jobrole/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JobRole>> getAllJobRoles() {
		List<JobRole> jobRoleList = jobRoleService.findAll();
		return new ResponseEntity<>(jobRoleList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/jobTitle/{departmentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JobTitle>> getAllJobTitle(@PathVariable String departmentId) {
		Long id=Long.parseLong(departmentId);
		Department department=departmentService.findById(id);
		List<JobTitle> jobTitleList = jobTitleService.findByDepartment(department);
		return new ResponseEntity<>(jobTitleList, HttpStatus.OK);
	}

	@RequestMapping(value = "/applications", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Application>> getApplicationList() {
		List<Application> jobRoleList = applicationService.findAll();
		return new ResponseEntity<>(jobRoleList, HttpStatus.OK);
	}

	@RequestMapping(value = "/specialrole/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SpecialRole>> getSpecialRolesList() {
		List<SpecialRole> specialRoleList = specialRoleService.findAll();
		return new ResponseEntity<>(specialRoleList, HttpStatus.OK);
	}

	@RequestMapping(value = "/permissiongroups/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity savePermissionGroupDetails(@RequestBody DistributionGroupMapping assignmentsVO) {
		 distriGroupAssignmentService.save(assignmentsVO);
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/permissiongroups/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PermissionGroup>> getPermissionGroupDetails() {
		List<PermissionGroup> permissiongrouplist= permissionGroupService.findAll();
		return new ResponseEntity<>(permissiongrouplist, HttpStatus.OK);
	}

/*	@RequestMapping(value = "/addvalue/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addValue(@RequestBody NewValue valueVO) {
		baseServiceImpl.addNewValue(valueVO);
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}*/

	
	@RequestMapping(value = "/saveDistributionGroup/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity saveDistributionGroup(@RequestBody DistributionGroup groupVO) {
		distributionGroupService.save(groupVO);
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/distributiongrouplist/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<DistributionGroup>> getDistributionList() {
		List<DistributionGroup> distributionGroupList=distributionGroupService.findAll();
		DistributionList distroList=new DistributionList();
		Set<String> distroSet=new HashSet<>();
		for(DistributionGroup distributionGroup:distributionGroupList) {
			distroSet.add(distributionGroup.getName());
			
		}
		System.out.println(distributionGroupList.size());
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity <>(distributionGroupList, HttpStatus.OK);
	}

	@RequestMapping(value = "/dailyDistributiongrouplist/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<DistributionGroup> > getDailyDistributionList() {
		List<DistributionGroup> distributionGroupList =dailyDistributionService.findAll();
		return new ResponseEntity<>(distributionGroupList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/xternalDistributionlist/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<XternalDistributionGroup> > xternalDistributionlist() {
		List<XternalDistributionGroup> xterlDistributionGrouplist =xterlDistributionGroupService.findAll();
		return new ResponseEntity<>(xterlDistributionGrouplist, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getSpecialRoleMapping/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<SpecialRoleMapping> > getSpecialRoleMapping() {
		List<SpecialRoleMapping> distributionGroupList =specialRoleMappingService.findAll();
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(distributionGroupList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getPermissionGroupMapping/{permissiongroupId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PermissionGroupMapping> getPermissionGroupMapping(@PathVariable String permissiongroupId) {	
		long permissionlong=Long.parseLong(permissiongroupId);
		 PermissionGroup permissionGroup=permissionGroupService.findById(permissionlong);
		 PermissionGroupMapping permissionGroupMapping=permissionGroupMappingService.findByPermissionGroup(permissionGroup);
		return new ResponseEntity<>(permissionGroupMapping, HttpStatus.OK);
	}

	@RequestMapping(value = "/inviteUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity savePermissionGroupDetails(@RequestBody List<EmpDetail> EmpDetailList) {
		for(EmpDetail emp:EmpDetailList)
		{
			EmpDetail empDetailobj=empDetailService.findById(emp.getId());
			empDetailobj.setIsInvited(emp.getIsInvited());
			empDetailService.save(empDetailobj);
		}
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	

	@RequestMapping(value = "/usertype/{unitId}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserType>> getUserType(@PathVariable String unitId){
		long id=Long.parseLong(unitId);
		Unit unit=unitService.findbyId(id);
		List<UserType> userTypesList = userTypeService.findByUnit(unit);
		return new ResponseEntity<>(userTypesList,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sharedDocPrivilege",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SharedDocPrivilege>> getSharedPrivilegeByInfo(){

		List<SharedDocPrivilege> sharedDocPrivilege = sharedDocPrivilegeService.findAll();
		return new ResponseEntity<>(sharedDocPrivilege, HttpStatus.OK);
	}
	@RequestMapping(value = "/deptDocPrivilege",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DeptDocPrivilege>> deptDocPrivilege(){
		List<DeptDocPrivilege> deptDocPrivilege = deptDocPrivilegeService.findAll();
		return new ResponseEntity<>(deptDocPrivilege, HttpStatus.OK);
	}
	@RequestMapping(value = "/otherDocPrivilege",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OtherDocPrivilege>> otherDocPrivilege(){
		List<OtherDocPrivilege> otherDocPrivilege = otherDocPrivilegeService.findAll();
		return new ResponseEntity<>(otherDocPrivilege, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getDeviceInfo",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DeviceInfo>> deviceInfo(){
		List<DeviceInfo> deviceInfo = deviceInfoService.findAll();
		return new ResponseEntity<>(deviceInfo, HttpStatus.OK);
	}
}