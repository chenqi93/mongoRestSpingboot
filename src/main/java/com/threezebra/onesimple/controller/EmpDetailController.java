package com.threezebra.onesimple.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.threezebra.common.TokenGeneratorUtil;
import com.threezebra.domain.AdditionalLocation;
import com.threezebra.domain.Application;
import com.threezebra.domain.BaseLocation;
import com.threezebra.domain.BaseResponse;
import com.threezebra.domain.CallSheet;
import com.threezebra.domain.DailyDistributionGroup;
import com.threezebra.domain.Department;
import com.threezebra.domain.DeptDocPrivilege;
import com.threezebra.domain.DeviceInfo;
import com.threezebra.domain.DistributionGroup;
import com.threezebra.domain.DistributionGroupMapping;
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
import com.threezebra.onesimple.dto.FindLocationJson;
import com.threezebra.onesimple.dto.JobRoleFinderJson;
import com.threezebra.restapi.ValidatingUserRepositoryDecorator;
import com.threezebra.service.AdditionalLocationService;
import com.threezebra.service.ApplicationService;
import com.threezebra.service.BaseLocationService;
import com.threezebra.service.CallSheetService;
import com.threezebra.service.DailyDistributionGroupService;
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

	private static final Logger logger = LogManager.getLogger(EmpDetailController.class);

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
	DailyDistributionGroupService dailyDistributionService;
	@Autowired
	PermissionGroupService permissionGroupService;
	@Autowired
	JobTitleService jobTitleService;
	@Autowired
	CallSheetService callSheetService;
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
	DailyDistributionGroupService dailydDistributionGroupService;
	@Value("${prefix.distroname}")
	private String distroname;
	@Value("${prefix.dailydistroname}")
	private String dailydistroname;
	@Value("${prefix.deptflag}")
	private String deptflag;
	@Value("${prefix.unitFlag}")
	private String unitflag;
	@Value("${prefix.blocationFlag}")
	private String blocationFlag;
	@Value("${prefix.jobRoleFlag}")
	private String jobRoleFlag;
	@Value("${prefix.userType}")
	private String userTypeflag;
	@Autowired
	private ValidatingUserRepositoryDecorator validatingUserRepositoryDecorator;

	// @PreAuthorize(USER)

	@RequestMapping(value = "/getBaseLocation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BaseLocation>> getBaseLocation() {
		// System.out.println("username::::::" + userName);
		// validatingUserRepositoryDecorator.findAccountValidated(userName);
		List<BaseLocation> baseLocationList = baseLocationService.findAll();
		return new ResponseEntity<List<BaseLocation>>(baseLocationList, HttpStatus.OK);
	}

	@RequestMapping(value = "/getEmpDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmpDetail>> listAllEmpDetails() {
		logger.info("EmpDetailController :: listAllEmpDetails :: start");
		// validatingUserRepositoryDecorator.findAccountValidated(userName);

		List<EmpDetail> EmpDetailList = empDetailService.findAll();
		return new ResponseEntity<List<EmpDetail>>(EmpDetailList, HttpStatus.OK);
	}

	@RequestMapping(value = "/createEmpDetail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity saveEmpDetailInfo(@RequestBody EmployeeJson employeeJson) {
		BaseResponse response = null;
		List<EmpDetail> empExistDetails = null;
		boolean validationStatus = false;
		try {
			empExistDetails = empDetailService.findDuplicateEmployee(employeeJson.getPersonalPhoneNum(),
					employeeJson.getPersonalEmail());
			if (empExistDetails != null && empExistDetails.size() > 0) {
				validationStatus = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (validationStatus) {
			response = new BaseResponse("100", "Employee already exists.");
		} else {
			List<DistributionGroup> distroGroupList = new ArrayList<>();
			UserType userType = userTypeService.findById(employeeJson.getUserType());
			EmpDetail empDetails = new EmpDetail();
			empDetails.setId(System.nanoTime());
			empDetails.setFirstName(employeeJson.getFirstName());
			empDetails.setLastName(employeeJson.getLastName());
			empDetails.setAccessEndDate(employeeJson.getAccessEndDate());
			empDetails.setAccessRenwStartDate(employeeJson.getAccessRenwStartDate());
			empDetails.setAccessStartDate(employeeJson.getAccessStartDate());
			empDetails.setAccessSusStartDate(employeeJson.getAccessSusStartDate());
			BaseLocation blocation = null;
			if (employeeJson.getBaseLocation() != 0) {
				blocation = baseLocationService.findbyId(employeeJson.getBaseLocation());
				empDetails.setBaseLocation(blocation);
			}
			Unit unit = unitService.findbyId(employeeJson.getUnit());
			Department department = departmentService.findById(employeeJson.getDepartment());
			JobRole jobRole = jobRoleService.findById(employeeJson.getJobRole());
			empDetails.setJobRole(jobRole);
			StringBuilder permissiongroupname = new StringBuilder();
			if (blocationFlag.equals("TRUE")) {
				permissiongroupname.append(blocation.getName());
			}

			if (unitflag.equals("TRUE")) {
				permissiongroupname.append(".").append(unit.getName());
			}
			if (deptflag.equals("TRUE")) {
				permissiongroupname.append(".").append(department.getName());
			}
			if (jobRoleFlag.equals("TRUE")) {
				permissiongroupname.append(".").append(jobRole.getName());
				;
			}
			/*
			 * permissiongroupname =
			 * permissiongroupname.append(blocation.getName()).append(".").append(unit.
			 * getName())
			 * .append(".").append(department.getName()).append(".").append(jobRole.getName(
			 * ));
			 */
			PermissionGroup permissionGroup = permissionGroupService.findByName(permissiongroupname.toString());
			if (null != permissionGroup) {
				empDetails.setPermissionGroup(permissionGroup);
			}
			if (!(jobRole.getName().equals("Daily"))) {
				StringBuilder distributionGroupname = new StringBuilder();

				if (deptflag.equals("TRUE")) {
					distributionGroupname.append(department.getName()).append("-Dept-");
				}
				if (unitflag.equals("TRUE")) {
					distributionGroupname.append(unit.getName()).append(".");
				}
				if (userTypeflag.equals("TRUE")) {
					distributionGroupname.append(userType.getName());
				}
				if (blocationFlag.equals("TRUE")) {
					distributionGroupname.append(".").append(blocation.getName());
				}
				/*
				 * distroGroup.append("Distro-").append(department.getName()).append("-Dept-").
				 * append(unit.getName()) .append(".").append(blocation.getName());
				 */
				DistributionGroup distroGroupObj = distributionGroupService
						.findByName(distributionGroupname.toString());
				if (null != distroGroupObj) {
					distroGroupList.add(distroGroupObj);
					empDetails.setDistributionGroup(distroGroupList);
				}
			} else {
				StringBuilder distributionGroupname = new StringBuilder();
				distributionGroupname.append(distroname);
				if (deptflag.equals("TRUE")) {
					distributionGroupname.append(department.getName()).append("-Dept-");
				}
				if (unitflag.equals("TRUE")) {
					distributionGroupname.append(unit.getName()).append(dailydistroname);
				}
				if (blocationFlag.equals("TRUE")) {
					distributionGroupname.append(".").append(blocation.getName());
				}
				/*
				 * distributionGroupname.append("Daily.").append(department.getName()).append(
				 * "-DAILYHIRES-")
				 * .append(unit.getName()).append(".").append(blocation.getName());
				 */
				DailyDistributionGroup distroGroupObj = dailydDistributionGroupService
						.findByName(distributionGroupname.toString());
				if (null != distroGroupObj) {
					empDetails.setDialyDistributionGroup(distroGroupObj);
				}
			}
			if (department.getName().equals("Xternal")) {
				StringBuilder xternaldistributionGroupname = new StringBuilder();
				xternaldistributionGroupname.append("Distro-Xternal").append(jobRole.getName());
				XternalDistributionGroup xdistroGroupObj = xterlDistributionGroupService
						.findByName(xternaldistributionGroupname.toString());
				if (null != xdistroGroupObj) {
					empDetails.setXternalDistributionGroup(xdistroGroupObj);
				}
			}
			empDetails.setUnit(unit);
			empDetails.setDepartment(department);
			long[] additionalarr = employeeJson.getAdditionalLocation();
			if (null != additionalarr) {
				for (int i = 0; i < additionalarr.length; i++) {
					AdditionalLocation addloc = additionalLocationService.findByName(additionalarr[i]);
					List<AdditionalLocation> addloclist = new ArrayList<>();
					addloclist.add(addloc);
					empDetails.setAdditionalLocation(addloclist);
				}
			}
			String[] deviceIssuedarr = employeeJson.getDeviceIssued();
			List<DeviceInfo> deviceIssued = new ArrayList<>();
			if (null != deviceIssuedarr) {
				for (int i = 0; i < deviceIssuedarr.length; i++) {
					DeviceInfo deviceInfo = deviceInfoService.save(deviceIssuedarr[i]);
					deviceIssued.add(deviceInfo);
				}
			}
			empDetails.setDeviceIssued(deviceIssued);
			SpecialRole specialRole = specialRoleService.findById(employeeJson.getSpecialRole());
			empDetails.setSpecialRole(specialRole);
			empDetails.setUserType(userType);
			empDetails.setIsActive("1");
			empDetails.setDeleted(employeeJson.getDeleted());
			empDetails.setIsInvited(employeeJson.getIsInvited());
			JobTitle jobTitle = jobTitleService.findById(employeeJson.getJobTitle());
			empDetails.setJobTitle(jobTitle);
			empDetails.setPermittedNumDevices(employeeJson.getPermittedNumDevices());
			empDetails.setPersonalEmail(employeeJson.getPersonalEmail());
			empDetails.setPersonalPhoneNum(employeeJson.getPersonalPhoneNum());
			empDetails.setUniqueId(employeeJson.getUniqueId());
			empDetails.setWorkEmail(employeeJson.getWorkEmail());
			empDetails.setSaveFlag(employeeJson.getSaveFlag());
			empDetailService.save(empDetails);
			response = new BaseResponse("200", "SUCCESS");
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/unit/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Unit>> getUnits() {
		List<Unit> unitList = unitService.findAll();
		return new ResponseEntity<>(unitList, HttpStatus.OK);
	}

	@RequestMapping(value = "/department/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Department>> getDepartment() {
		List<Department> deptList = departmentService.findAll();
		return new ResponseEntity<>(deptList, HttpStatus.OK);
	}

	@RequestMapping(value = "/department/{unitName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Department>> getDepartmentbyUnit(@PathVariable String unitName) {
		long id = Long.parseLong(unitName);
		Unit unit = unitService.findbyId(id);
		List<Department> deptList = departmentService.findByUnit(unit);
		return new ResponseEntity<>(deptList, HttpStatus.OK);
	}

	@RequestMapping(value = "/usertype/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserType>> getUserType() {
		List<UserType> userTypesList = userTypeService.findAll();
		return new ResponseEntity<>(userTypesList, HttpStatus.OK);
	}

	public boolean containsName(final List<Unit> unitlist, final String name) {
		return unitlist.stream().filter(o -> o.getName().equals(name)).findFirst().isPresent();
	}

	@RequestMapping(value = "/jobrole", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JobRole>> getAllJobRoles() {
		List<JobRole> jbRoleList = jobRoleService.findAll();
		return new ResponseEntity<>(jbRoleList, HttpStatus.OK);
	}

	@RequestMapping(value = "/findJobRoleByUnitAndUserType", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JobRole>> findJobRoleByUnitAndUserType(@RequestBody JobRoleFinderJson jobRolefinder) {
		List<JobRole> jbRoleList = new ArrayList<>();
		List<UserType> userTypeList=jobRolefinder.getUserTypeList();
		for (UserType userType : userTypeList) {
			List<JobRole> jobRoleList = jobRoleService.findByUserType(userType);
			for (JobRole jobRole : jobRoleList) {
				List<Unit> unitList = jobRole.getUnit();
				if (containsName(unitList, jobRolefinder.getUnit().getName()) == true  && userType.getCheckFlag().equals("TRUE")) {
					jbRoleList.add(jobRoleService.updateFlag(jobRole, "TRUE"));

				} else {
					jbRoleList.add(jobRoleService.updateFlag(jobRole, "FALSE"));
				}
			}
		}
		return new ResponseEntity<>(jbRoleList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findJobRoleByUserType", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JobRole>> findJobRoleByUserType(@RequestBody JobRoleFinderJson jobRolefinder) {
		List<JobRole> jbRoleList = new ArrayList<>();
		List<UserType> userTypeList=jobRolefinder.getUserTypeList();
		for (UserType userType : userTypeList) {
			List<JobRole> jobRoleList = jobRoleService.findByUserType(userType);
			logger.info("Get job role object size"+jobRoleList.size());
			for (JobRole jobRole : jobRoleList) {
				List<Unit> unitList = jobRole.getUnit();
				if (containsName(unitList, jobRolefinder.getUnit().getName()) == true  && userType.getCheckFlag().equals("TRUE")) {
					jbRoleList.add(jobRoleService.updateFlag(jobRole, "TRUE"));

				} else {
					jbRoleList.add(jobRoleService.updateFlag(jobRole, "FALSE"));
				}
			}
		}
		return new ResponseEntity<>(jbRoleList, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/jobTitle/{departmentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JobTitle>> getAllJobTitle(@PathVariable String departmentId) {
		Long id = Long.parseLong(departmentId);
		Department department = departmentService.findById(id);
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
		List<PermissionGroup> permissiongrouplist = permissionGroupService.findAll();
		return new ResponseEntity<>(permissiongrouplist, HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value = "/addvalue/", method = RequestMethod.POST, consumes =
	 * MediaType.APPLICATION_JSON_VALUE) public ResponseEntity addValue(@RequestBody
	 * NewValue valueVO) { baseServiceImpl.addNewValue(valueVO); BaseResponse
	 * response = new BaseResponse("200", "SUCCESS"); return new
	 * ResponseEntity<>(response, HttpStatus.OK); }
	 */

	@RequestMapping(value = "/saveDistributionGroup/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity saveDistributionGroup(@RequestBody DistributionGroup groupVO) {
		distributionGroupService.save(groupVO);
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/distributiongrouplist/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DistributionGroup>> getDistributionList() {
		List<DistributionGroup> distributionGroupList = distributionGroupService.findAll();
		System.out.println(distributionGroupList.size());
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(distributionGroupList, HttpStatus.OK);
	}

	@RequestMapping(value = "/dailyDistributiongrouplist/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DailyDistributionGroup>> getDailyDistributionList() {
		List<DailyDistributionGroup> distributionGroupList = dailyDistributionService.findAll();
		return new ResponseEntity<>(distributionGroupList, HttpStatus.OK);
	}

	@RequestMapping(value = "/getCallSheet/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CallSheet>> geCallSheetList() {
		List<CallSheet> callSheetList = callSheetService.findAll();
		return new ResponseEntity<>(callSheetList, HttpStatus.OK);
	}

	@RequestMapping(value = "/xternalDistributionlist/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<XternalDistributionGroup>> xternalDistributionlist() {
		List<XternalDistributionGroup> xterlDistributionGrouplist = xterlDistributionGroupService.findAll();
		return new ResponseEntity<>(xterlDistributionGrouplist, HttpStatus.OK);
	}

	@RequestMapping(value = "/getSpecialRoleMapping/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SpecialRoleMapping>> getSpecialRoleMapping() {
		List<SpecialRoleMapping> distributionGroupList = specialRoleMappingService.findAll();
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(distributionGroupList, HttpStatus.OK);
	}

	@RequestMapping(value = "/getSpecialRoleMapping/{specialRoleId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SpecialRoleMapping> getSpecialRoleMapping(@PathVariable long specialRoleId) {
		SpecialRole specialRole = specialRoleService.findById(specialRoleId);
		SpecialRoleMapping specialRoleMapping = specialRoleMappingService.findBySpecialRole(specialRole);
		return new ResponseEntity<>(specialRoleMapping, HttpStatus.OK);
	}

	@RequestMapping(value = "/getPermissionGroupMapping/{permissiongroupId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PermissionGroupMapping> getPermissionGroupMapping(@PathVariable String permissiongroupId) {
		long permissionlong = Long.parseLong(permissiongroupId);
		PermissionGroup permissionGroup = permissionGroupService.findById(permissionlong);
		PermissionGroupMapping permissionGroupMapping = permissionGroupMappingService
				.findByPermissionGroup(permissionGroup);
		return new ResponseEntity<>(permissionGroupMapping, HttpStatus.OK);
	}

	@RequestMapping(value = "/inviteUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity savePermissionGroupDetails(@RequestBody List<EmpDetail> EmpDetailList) {
		for (EmpDetail emp : EmpDetailList) {
			EmpDetail empDetailobj = empDetailService.findById(emp.getId());
			empDetailobj.setIsInvited(emp.getIsInvited());
			empDetailobj.setIsActive(emp.getIsActive());
			empDetailService.save(empDetailobj);
		}
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/usertype/{unitId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserType>> getUserType(@PathVariable String unitId) {
		long id = Long.parseLong(unitId);
		Unit unit = unitService.findbyId(id);
		List<UserType> userTypesLst=new ArrayList<>();
		List<UserType> userTypesList = userTypeService.findByUnit(unit);
		for (UserType userType : userTypesList) {
			List<Unit> unitList = userType.getUnit();
				if (containsName(unitList, unit.getName()) == true) {
					userTypesLst.add(userTypeService.updateFlag(userType, "TRUE"));
				} else {
					userTypesLst.add(userTypeService.updateFlag(userType, "FALSE"));
				}
			}
		
		return new ResponseEntity<>(userTypesLst, HttpStatus.OK);
	}

	@RequestMapping(value = "/sharedDocPrivilege", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SharedDocPrivilege>> getSharedPrivilegeByInfo() {

		List<SharedDocPrivilege> sharedDocPrivilege = sharedDocPrivilegeService.findAll();
		return new ResponseEntity<>(sharedDocPrivilege, HttpStatus.OK);
	}

	@RequestMapping(value = "/deptDocPrivilege", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DeptDocPrivilege>> deptDocPrivilege() {
		List<DeptDocPrivilege> deptDocPrivilege = deptDocPrivilegeService.findAll();
		return new ResponseEntity<>(deptDocPrivilege, HttpStatus.OK);
	}

	@RequestMapping(value = "/otherDocPrivilege", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OtherDocPrivilege>> otherDocPrivilege() {
		List<OtherDocPrivilege> otherDocPrivilege = otherDocPrivilegeService.findAll();
		return new ResponseEntity<>(otherDocPrivilege, HttpStatus.OK);
	}

	@RequestMapping(value = "/getDeviceInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DeviceInfo>> deviceInfo() {
		List<DeviceInfo> deviceInfo = deviceInfoService.findAll();
		return new ResponseEntity<>(deviceInfo, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAdditionalLocation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AdditionalLocation>> getAdditionalLocation() {
		List<AdditionalLocation> addtionalLocation = additionalLocationService.findAll();
		return new ResponseEntity<>(addtionalLocation, HttpStatus.OK);
	}

	@RequestMapping(value = "/saveEmpDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity tempSaveEmpDetails(@RequestBody EmployeeJson employeeJson) {
		BaseResponse response = null;
		List<EmpDetail> empExistDetails = null;
		boolean validationStatus = false;
		try {
			if (employeeJson.getPersonalPhoneNum() != null && employeeJson.getPersonalEmail() != null) {
				empExistDetails = empDetailService.findDuplicateEmployee(employeeJson.getPersonalPhoneNum(),
						employeeJson.getPersonalEmail());
			}
			if (empExistDetails != null && empExistDetails.size() > 0) {
				validationStatus = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (validationStatus) {
			response = new BaseResponse("100", "Employee already exists.");
		} else {
			List<DistributionGroup> distroGroupList = new ArrayList<>();
			EmpDetail empDetails = new EmpDetail();
			if (employeeJson.getId() == 0) {
				empDetails.setId(System.nanoTime());
			} else {
				empDetails.setId(employeeJson.getId());
			}
			empDetails.setFirstName(employeeJson.getFirstName());
			empDetails.setLastName(employeeJson.getLastName());
			empDetails.setIsActive("1");
			empDetails.setAccessEndDate(employeeJson.getAccessEndDate());
			empDetails.setAccessRenwStartDate(employeeJson.getAccessRenwStartDate());
			empDetails.setAccessStartDate(employeeJson.getAccessStartDate());
			empDetails.setAccessSusStartDate(employeeJson.getAccessSusStartDate());
			BaseLocation blocation = null;

			if (employeeJson.getBaseLocation() != 0) {
				blocation = baseLocationService.findbyId(employeeJson.getBaseLocation());
				empDetails.setBaseLocation(blocation);
			}
			if (employeeJson.getUnit() != 0) {
				Unit unit = unitService.findbyId(employeeJson.getUnit());
				empDetails.setUnit(unit);
			}

			if (employeeJson.getDepartment() != 0) {
				Department department = departmentService.findById(employeeJson.getDepartment());
				empDetails.setDepartment(department);
			}

			if (employeeJson.getJobRole() != 0) {
				JobRole jobRole = jobRoleService.findById(employeeJson.getJobRole());
				empDetails.setJobRole(jobRole);
			}

			long[] additionalarr = employeeJson.getAdditionalLocation();
			if (null != additionalarr) {
				for (int i = 0; i < additionalarr.length; i++) {
					AdditionalLocation addloc = additionalLocationService.findByName(additionalarr[i]);
					List<AdditionalLocation> addloclist = new ArrayList<>();
					addloclist.add(addloc);
					empDetails.setAdditionalLocation(addloclist);
				}
			}
			long[] deviceIssuedarr = employeeJson.getAdditionalLocation();
			List<DeviceInfo> deviceIssued = new ArrayList<>();
			if (null != deviceIssuedarr) {
				for (int i = 0; i < deviceIssuedarr.length; i++) {
					DeviceInfo deviceInfo = deviceInfoService.findByName(deviceIssuedarr[i]);
					deviceIssued.add(deviceInfo);
				}
			}
			empDetails.setDeviceIssued(deviceIssued);
			UserType userType = userTypeService.findById(employeeJson.getUserType());
			SpecialRole specialRole = specialRoleService.findById(employeeJson.getSpecialRole());
			empDetails.setSpecialRole(specialRole);
			empDetails.setUserType(userType);
			empDetails.setDeleted(employeeJson.getDeleted());
			empDetails.setIsInvited(employeeJson.getIsInvited());
			if (employeeJson.getJobTitle() != 0) {
				JobTitle jobTitle = jobTitleService.findById(employeeJson.getJobTitle());
				empDetails.setJobTitle(jobTitle);
			}
			empDetails.setPermittedNumDevices(employeeJson.getPermittedNumDevices());
			empDetails.setPersonalEmail(employeeJson.getPersonalEmail());
			empDetails.setPersonalPhoneNum(employeeJson.getPersonalPhoneNum());
			empDetails.setUniqueId(employeeJson.getUniqueId());
			empDetails.setWorkEmail(employeeJson.getWorkEmail());
			empDetails.setSaveFlag(employeeJson.getSaveFlag());
			empDetailService.save(empDetails);

		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateEmpDetail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateEmpDetailInfo(@RequestBody EmployeeJson employeeJson) {
		BaseResponse response = null;
		List<EmpDetail> empDetails = null;
		boolean validationStatus = false;
		try {
			empDetails = empDetailService.findDuplicateEmployee(employeeJson.getPersonalPhoneNum(),
					employeeJson.getPersonalEmail());
			if (empDetails != null && empDetails.size() > 0 && employeeJson.getId() != empDetails.get(0).getId()) {
				response = new BaseResponse("100", "Employee already exists.");
			} else {
				EmpDetail empDetail = new EmpDetail();
				empDetail.setId(employeeJson.getId());
				List<DistributionGroup> distroGroupList = new ArrayList<>();
				empDetail.setFirstName(employeeJson.getFirstName());
				empDetail.setLastName(employeeJson.getLastName());
				empDetail.setAccessEndDate(employeeJson.getAccessEndDate());
				empDetail.setAccessRenwStartDate(employeeJson.getAccessRenwStartDate());
				empDetail.setAccessStartDate(employeeJson.getAccessStartDate());
				empDetail.setAccessSusStartDate(employeeJson.getAccessSusStartDate());
				UserType userType = userTypeService.findById(employeeJson.getUserType());
				System.out.println("Before Base location");
				BaseLocation blocation = baseLocationService.findbyId(employeeJson.getBaseLocation());
				System.out.println("After Base location");
				empDetail.setBaseLocation(blocation);
				System.out.println("Before Unit");
				Unit unit = unitService.findbyId(employeeJson.getUnit());
				System.out.println("After Unit");
				Department department = departmentService.findById(employeeJson.getDepartment());
				JobRole jobRole = jobRoleService.findById(employeeJson.getJobRole());
				empDetail.setJobRole(jobRole);
				StringBuilder permissiongroupname = new StringBuilder();
				if (blocationFlag.equals("TRUE")) {
					permissiongroupname.append(blocation.getName());
				}

				if (unitflag.equals("TRUE")) {
					permissiongroupname.append(".").append(unit.getName());
				}
				if (deptflag.equals("TRUE")) {
					permissiongroupname.append(".").append(department.getName());
				}
				if (jobRoleFlag.equals("TRUE")) {
					permissiongroupname.append(".").append(jobRole.getName());
					;
				}
				/*
				 * permissiongroupname =
				 * permissiongroupname.append(blocation.getName()).append(".").append(unit.
				 * getName())
				 * .append(".").append(department.getName()).append(".").append(jobRole.getName(
				 * ));
				 */
				PermissionGroup permissionGroup = permissionGroupService.findByName(permissiongroupname.toString());
				if (null != permissionGroup) {
					empDetail.setPermissionGroup(permissionGroup);
				}
				if (!(jobRole.getName().equals("Daily"))) {
					StringBuilder distroGroup = new StringBuilder();

					distroGroup.append(distroname);
					if (deptflag.equals("TRUE")) {
						distroGroup.append(department.getName()).append("-Dept-");
					}
					if (unitflag.equals("TRUE")) {
						distroGroup.append(unit.getName());
					}
					if (blocationFlag.equals("TRUE")) {
						distroGroup.append(".").append(blocation.getName());
					}
					/*
					 * distroGroup.append("Distro-").append(department.getName()).append("-Dept-").
					 * append(unit.getName()) .append(".").append(blocation.getName());
					 */
					DistributionGroup distroGroupObj = distributionGroupService.findByName(distroGroup.toString());
					if (null != distroGroupObj) {
						distroGroupList.add(distroGroupObj);
						empDetail.setDistributionGroup(distroGroupList);
					}
				} else {
					StringBuilder distributionGroupname = new StringBuilder();
					distributionGroupname.append(distroname);

					if (!(jobRole.getName().equals("Daily"))) {

						if (deptflag.equals("TRUE")) {
							distributionGroupname.append(department.getName()).append("-Dept-");
						}
						if (unitflag.equals("TRUE")) {
							distributionGroupname.append(unit.getName()).append(".");
						}
						if (userTypeflag.equals("TRUE")) {
							distributionGroupname.append(userType.getName());
						}
						if (blocationFlag.equals("TRUE")) {
							distributionGroupname.append(".").append(blocation.getName());
						}
						/*
						 * distributionGroupname.append("Daily.").append(department.getName()).append(
						 * "-DAILYHIRES-")
						 * .append(unit.getName()).append(".").append(blocation.getName());
						 */
						DailyDistributionGroup distroGroupObj = dailydDistributionGroupService
								.findByName(distributionGroupname.toString());
						if (null != distroGroupObj) {
							empDetail.setDialyDistributionGroup(distroGroupObj);
						}
					}
					if (department.getName().equals("Xternal")) {
						StringBuilder xternaldistributionGroupname = new StringBuilder();
						xternaldistributionGroupname.append("Distro-Xternal").append(jobRole.getName());
						XternalDistributionGroup xdistroGroupObj = xterlDistributionGroupService
								.findByName(xternaldistributionGroupname.toString());
						if (null != xdistroGroupObj) {
							empDetail.setXternalDistributionGroup(xdistroGroupObj);
						}
					}
					empDetail.setUnit(unit);
					empDetail.setDepartment(department);
					long[] additionalarr = employeeJson.getAdditionalLocation();
					if (null != additionalarr) {
						for (int i = 0; i < additionalarr.length; i++) {
							AdditionalLocation addloc = additionalLocationService.findByName(additionalarr[i]);
							List<AdditionalLocation> addloclist = new ArrayList<>();
							addloclist.add(addloc);
							empDetail.setAdditionalLocation(addloclist);
						}
					}
					long[] deviceIssuedarr = employeeJson.getAdditionalLocation();
					List<DeviceInfo> deviceIssued = new ArrayList<>();
					if (null != deviceIssuedarr) {
						for (int i = 0; i < deviceIssuedarr.length; i++) {
							DeviceInfo deviceInfo = deviceInfoService.findByName(deviceIssuedarr[i]);
							deviceIssued.add(deviceInfo);
						}
					}
					empDetail.setDeviceIssued(deviceIssued);
					empDetail.setIsActive("1");
					SpecialRole specialRole = specialRoleService.findById(employeeJson.getSpecialRole());
					empDetail.setSpecialRole(specialRole);
					empDetail.setUserType(userType);
					empDetail.setDeleted(employeeJson.getDeleted());
					empDetail.setIsInvited(employeeJson.getIsInvited());
					JobTitle jobTitle = jobTitleService.findById(employeeJson.getJobTitle());
					empDetail.setJobTitle(jobTitle);
					empDetail.setPermittedNumDevices(employeeJson.getPermittedNumDevices());
					empDetail.setPersonalEmail(employeeJson.getPersonalEmail());
					empDetail.setPersonalPhoneNum(employeeJson.getPersonalPhoneNum());
					empDetail.setUniqueId(employeeJson.getUniqueId());
					empDetail.setWorkEmail(employeeJson.getWorkEmail());
					empDetailService.save(empDetail);
					response = new BaseResponse("200", "SUCCESS");
					validationStatus = true;

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateByLocation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity findByAdditionalLocation(@RequestBody FindLocationJson findLocation) {
		BaseResponse response = null;
		List<AdditionalLocation> additionalLocationList = findLocation.getAdditionalLocation();
		String employeeflag=findLocation.getEmployeeListflag();
		List<Unit> unitlist = new ArrayList<>();
		List<Department> deptlist = new ArrayList<>();
		List<UserType> usertypeList = new ArrayList<>();
		List<JobRole> jobRolelist = new ArrayList<>();
		List<String> locationlist = new ArrayList<>();
		if (null != additionalLocationList) {
			for (AdditionalLocation additionalLocation : additionalLocationList) {
				List<EmpDetail> empDetailList = empDetailService.findByAdditionalLocation(additionalLocation);
				if (null != empDetailList) {
					for (EmpDetail empdetail : empDetailList) {
						unitlist.add(empdetail.getUnit());
						deptlist.add(empdetail.getDepartment());
						usertypeList.add(empdetail.getUserType());
						jobRolelist.add(empdetail.getJobRole());
					}
					DistributionGroup distrogroup = distributionGroupService.createDistributionGroup(
							findLocation.getDefaultvalue(), locationlist, findLocation.getDistributionGroup(), unitlist,
							deptlist, usertypeList, jobRolelist,employeeflag,findLocation.getIsActive());
					for (EmpDetail empdetailObj : empDetailList) {
						List<DistributionGroup> distributionGroupList = empdetailObj.getDistributionGroup();
						distributionGroupList.add(distrogroup);
						empdetailObj.setDistributionGroup(distributionGroupList);
						empDetailService.save(empdetailObj);
					}
				}
			}
			response = new BaseResponse("200", "SUCCESS");
		} else {
			BaseLocation baseLocation = findLocation.getBaseLocation();
			if (null != baseLocation) {
				List<EmpDetail> empDetailList = empDetailService.findAll();
				DistributionGroup distrogroup = distributionGroupService.createDistributionGroup(
						findLocation.getDefaultvalue(), locationlist, findLocation.getDistributionGroup(), unitlist,
						deptlist, usertypeList, jobRolelist,employeeflag,findLocation.getIsActive());
				if (null != empDetailList) {
					for (EmpDetail empdetail : empDetailList) {
						List<DistributionGroup> distributionGroupList = empdetail.getDistributionGroup();
						distributionGroupList.add(distrogroup);
						empdetail.setDistributionGroup(distributionGroupList);
						empDetailService.save(empdetail);
					}
				}
			}
			response = new BaseResponse("200", "SUCCESS");
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/checkDraftEmp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity checkSaveAsDraftData(@RequestBody EmployeeJson employeeJson) {

		BaseResponse response = null;
		List<EmpDetail> empExistDetails = null;
		boolean validationStatus = false;
		try {
			if (employeeJson.getPersonalPhoneNum() != null && employeeJson.getPersonalEmail() != null) {
				empExistDetails = empDetailService.findDuplicateEmployee(employeeJson.getPersonalPhoneNum(),
						employeeJson.getPersonalEmail());
			}
			if (empExistDetails != null && empExistDetails.size() > 0) {
				validationStatus = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (validationStatus) {
			response = new BaseResponse("100", "Employee already exists.");
		} else {
			List<EmpDetail> allEmpDetails = empDetailService.findDupSaveEmp(employeeJson);
			if (allEmpDetails != null && allEmpDetails.size() > 0) {
				response = new BaseResponse("101", "Saved Employee already exists.");
			} else {
				response = new BaseResponse("200", "Employee already exists.");
			}
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/overrideEmp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity overrideEmpData(@RequestBody EmployeeJson employeeJson) {

		BaseResponse response = null;
		List<EmpDetail> empExistDetails = null;
		boolean validationStatus = false;
		try {
			if (employeeJson.getPersonalPhoneNum() != null && employeeJson.getPersonalEmail() != null) {
				empExistDetails = empDetailService.findDuplicateEmployee(employeeJson.getPersonalPhoneNum(),
						employeeJson.getPersonalEmail());
			}
			if (empExistDetails != null && empExistDetails.size() > 0) {
				validationStatus = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (validationStatus) {
			response = new BaseResponse("100", "Employee already exists.");
		} else {
			List<EmpDetail> allEmpDetails = empDetailService.findDupSaveEmp(employeeJson);
			empDetailService.remove(allEmpDetails);
			response = new BaseResponse("200", "Success");
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createADUser/{employeeID}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createADUser(@RequestHeader(value="Authorization") String authorization_token,@PathVariable String employeeID) {
		try {
			authorization_token = TokenGeneratorUtil.getAccessTokenForResource("https://graph.windows.net",authorization_token);
			EmpDetail empDetail = empDetailService.findById(Long.parseLong(employeeID));
			String empAzureObjID = empDetailService.createUserInAD(authorization_token, empDetail);
			if(empDetail != null) {
				List<PermissionGroup> groupObjects = permissionGroupService.getADGroups(authorization_token);
				if(groupObjects != null) {
					for(PermissionGroup groupObject : groupObjects) {
						 if(empDetail.getPermissionGroup().getName().equals(groupObject.getName())) {
							 empDetailService.mapUserAndGroup(empAzureObjID, groupObject.getAadObjectId(), authorization_token);
							 break;
						 }
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		BaseResponse response = new BaseResponse("200","SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/assignLicense/{user}/{application}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity assignAppLicense(@RequestHeader(value="Authorization") String authorization_token,@PathVariable("user")String userAccount,@PathVariable("application") String application) {
		int statusCode = 0;
		try {
			authorization_token = TokenGeneratorUtil.getAccessTokenForResource("https://graph.windows.net",authorization_token);
			statusCode = empDetailService.assignUserLicense(userAccount,application,authorization_token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BaseResponse response = new BaseResponse(String.valueOf(statusCode),"SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/createADGroups", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createADGroups(@RequestHeader(value="Authorization") String authorization_token) {
		try {
			authorization_token = TokenGeneratorUtil.getAccessTokenForResource("https://graph.windows.net",authorization_token);
			List<PermissionGroup> permissionGroups = permissionGroupService.findAll();
			if(permissionGroups != null) {
				for(PermissionGroup permissionGroup : permissionGroups) {
					permissionGroupService.crateADGroup(permissionGroup, authorization_token);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		BaseResponse response = new BaseResponse("200","SUCCESS");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteGroups", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteADGroups(@RequestHeader(value="Authorization") String authorization_token) {
		try {
			authorization_token = TokenGeneratorUtil.getAccessTokenForResource("https://graph.windows.net",authorization_token);
			List<PermissionGroup> groupObjects = permissionGroupService.getADGroups(authorization_token);
			if(groupObjects != null) {
				for(PermissionGroup groupObject : groupObjects) {
					int statusCode = permissionGroupService.deleteADGroup(groupObject.getAadObjectId(), authorization_token);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		BaseResponse response = new BaseResponse("200","SUCCESS");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/inviteToManager", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity inviteToManager(@RequestHeader(value="Authorization")String userAccessToken) {
		String authorization_token;
		int statusCode = 0;
		try {
			authorization_token = TokenGeneratorUtil.getAccessTokenForResource("https://outlook.office.com",userAccessToken);
			statusCode = empDetailService.inviteToManager(authorization_token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		BaseResponse response = new BaseResponse(String.valueOf(statusCode),"SUCCESS");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

}