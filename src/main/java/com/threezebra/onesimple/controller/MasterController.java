package com.threezebra.onesimple.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.threezebra.domain.BaseLocation;
import com.threezebra.domain.BaseResponse;
import com.threezebra.domain.CallSheet;
import com.threezebra.domain.DailyDistributionGroup;
import com.threezebra.domain.Department;
import com.threezebra.domain.DeptDocPrivilege;
import com.threezebra.domain.DistributionGroup;
import com.threezebra.domain.EmpDetail;
import com.threezebra.domain.JobRole;
import com.threezebra.domain.OtherDocPrivilege;
import com.threezebra.domain.PermissionGroup;
import com.threezebra.domain.SharedDocPrivilege;
import com.threezebra.domain.SpecialRole;
import com.threezebra.domain.SpecialRoleMapping;
import com.threezebra.domain.Unit;
import com.threezebra.domain.UserType;
import com.threezebra.domain.XternalDistributionGroup;
import com.threezebra.onesimple.dto.AdditionalLocationJson;
import com.threezebra.onesimple.dto.AllInfoJson;
import com.threezebra.onesimple.dto.ApplicationJson;
import com.threezebra.onesimple.dto.DeptDocPrivilegeJson;
import com.threezebra.onesimple.dto.DistributionGroupMappingJson;
import com.threezebra.onesimple.dto.JobRoleJson;
import com.threezebra.onesimple.dto.OtherDocPreveligesJson;
import com.threezebra.onesimple.dto.PermissionGroupMappingJson;
import com.threezebra.onesimple.dto.SharedDocPrivilegeJson;
import com.threezebra.onesimple.dto.SpecialJobRoleJson;
import com.threezebra.onesimple.dto.SpecialRoleMappingJson;
import com.threezebra.onesimple.dto.UnitDepartmentJson;
import com.threezebra.onesimple.dto.UnitDepartmentObjectJson;
import com.threezebra.onesimple.dto.UserTypeJson;
import com.threezebra.onesimple.dto.XternalListJson;
import com.threezebra.restapi.dto.JobTitleJson;
import com.threezebra.service.AdditionalLocationService;
import com.threezebra.service.ApplicationService;
import com.threezebra.service.BaseLocationService;
import com.threezebra.service.CallSheetService;
import com.threezebra.service.DailyDistributionGroupService;
import com.threezebra.service.DepartmentService;
import com.threezebra.service.DeptDocPrivilegeService;
import com.threezebra.service.DeviceInfoService;
import com.threezebra.service.DistributionGroupMappingService;
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
@RequestMapping("masterdata")
public class MasterController {
	Logger log = LoggerFactory.getLogger(MasterController.class);
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
	JobTitleService jobTitleService;

	@Autowired
	PermissionGroupService permissionGroupService;
	@Autowired
	SharedDocPrivilegeService sharedDocPrivilegeService;
	@Autowired
	SpecialRoleService specialRoleService;
	@Autowired
	ApplicationService applicationService;
	@Autowired
	DeptDocPrivilegeService deptDocPrivilegeService;
	@Autowired
	OtherDocPrivilegeService otherDocPrivilegeService;
	@Autowired
	DeviceInfoService deviceInfoService;
	@Autowired
	AdditionalLocationService additionalLocationService;
	@Autowired
	PermissionGroupMappingService permissionGroupMappingService;
	@Autowired
	DistributionGroupMappingService distributionGroupMappingService;
	@Autowired
	SpecialRoleMappingService specialRoleMappingService;
	@Autowired
	DistributionGroupService distributionGroupService;
	@Autowired
	XterlDistributionGroupService xterlDistributionGroupService;
	@Autowired
	DailyDistributionGroupService dailyDistributionGroupService;
	@Autowired
	CallSheetService callSheetService;
	@Autowired
	EmployeeService employeeService;

	private List<UserType> usertypelist;

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

	@RequestMapping(value = "/createUnitandDepartment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity saveDepartmentInfo(@RequestBody UnitDepartmentJson unitDepartmentJson) {

		List<Unit> unitlist = new ArrayList<>();
		BaseLocation baselocation = baseLocationService.save(unitDepartmentJson.getBaseLocation());
		String[] unitarr = unitDepartmentJson.getUnit();
		for (int i = 0; i < unitarr.length; i++) {
			unitlist.add(unitService.update(unitarr[i], baselocation));
		}
		String[] deptarr = unitDepartmentJson.getDepartment();
		for (int i = 0; i < deptarr.length; i++) {
			Department department = departmentService.findbyName(deptarr[i]);
			if (null != department) {
				for (Unit unit : unitlist) {
					departmentService.update(department, deptarr[i], unit);
				}
			} else {
				departmentService.save(deptarr[i], unitlist);
			}

		}
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateUnitandDepartment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateDepartmentInfo(@RequestBody UnitDepartmentObjectJson unitDepartmentObject) {
		Unit unit = unitDepartmentObject.getUnit();
		List<Department> departmentlist = unitDepartmentObject.getDepartment();
		List<UserType> usertypeList = unitDepartmentObject.getUsertype();
		for (Department department : departmentlist) {
			if (department.getCheckFlag().equals("TRUE")) {
				departmentService.update(department, department.getName(), unit);
			} else {
				List<Unit> unitList = department.getUnit();
				unitList.remove(unit);
				departmentService.updateDepartment(department, department.getName(), unitList);
			}

		}

		for (UserType userType : usertypeList) {
			List<Unit> untlst = userType.getUnit();
			List<JobRole> jobRoleList = userType.getJobRoleList();
			List<JobRole> jobRolelst = new ArrayList<>();
			if (userType.getCheckFlag().equals("TRUE")) {
				for (JobRole jobrole : jobRoleList) {
					JobRole jobRole = jobRoleService.findByName(jobrole.getName());
					jobRoleService.update(jobRole, unit, userType, jobrole.getCheckFlag());
					jobRolelst.add(jobRole);

				}
			}
		}
		createPermissionGroup();
		createDistributionGroup();
		createDailyDistributionGroup();
		createCallSheet();
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createUserType", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity saveUsertypeInfo(@RequestBody UserTypeJson unitUserTypes) {
		List<Unit> unitlist = new ArrayList<>();
		String[] unitarr = unitUserTypes.getUnit();
		for (int i = 0; i < unitarr.length; i++) {
			unitlist.add(unitService.findbyName(unitarr[i]));
		}
		String[] userTypearr = unitUserTypes.getUsertype();

		for (int i = 0; i < userTypearr.length; i++) {
			UserType userType = userTypeService.findByUserType(userTypearr[i]);

			if (null != userType) {
				userTypeService.update(userType, unitlist);
			} else {
				userTypeService.save(userTypearr[i], unitlist);
			}

		}

		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createPermissionGroup", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createPermissionGroup() {

		permissionGroupService.deleteAll();

		List<Unit> unitlist = unitService.findAll();
		List<Department> departmentlist = null;
		List<UserType> usertypelist = null;
		List<JobRole> jobRoleList = null;

		DeptDocPrivilege deptDocPrivilege = deptDocPrivilegeService.findByName("On-Line View, Print, Edit");
		SharedDocPrivilege sharedDocPrivilege = sharedDocPrivilegeService.findSharedDocPrivilege("On-Line View Only");
		List<Application> applications = applicationService.findAll();
		for (Unit unit : unitlist) {
			departmentlist = departmentService.findByUnit(unit);
			List<UserType> userTypelst = userTypeService.findByUnit(unit);
			if (userTypelst.size() > 0) {
				usertypelist = userTypelst;
			}

			for (Department department : departmentlist) {
				for (UserType userType : usertypelist) {
					jobRoleList = userType.getJobRoleList();
					for (JobRole jobRole : jobRoleList) {
						if (!(jobRole.getName().contains("InfoRecipient") || jobRole.getName().contains("Daily"))) {
							List<BaseLocation> baselocationlist = baseLocationService.findAll();
							for (BaseLocation baseLocation : baselocationlist) {
								PermissionGroup permissionGroup = new PermissionGroup();
								StringBuilder permissiongroupname = new StringBuilder();
								permissionGroup.setApplication(applications);
								permissionGroup.setId(System.nanoTime());
								permissionGroup.setUnit(unit.getName());
								permissionGroup.setJobRole(jobRole.getName());
								permissionGroup.setDepartment(department.getName());
								permissionGroup.setDeptDocPrivilege(deptDocPrivilege);
								permissionGroup.setSharedDocPrivilege(sharedDocPrivilege);
								if (blocationFlag.equals("TRUE")) {
									permissiongroupname.append(baseLocation.getName());
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

								log.info("%%%%%%%%%%%%%%" + permissiongroupname, "group name is");
								int count = 0;
								log.info("%%%%%%%%%" + count++, "no of records");
								permissionGroup.setName(permissiongroupname.toString());
								permissionGroupService.save(permissionGroup);
							}

						}

					}
				}

			}
		}

		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createJobRole", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity saveJobRoleInfo(@RequestBody JobRoleJson jobRolesJson) {
		String[] usertType = jobRolesJson.getUsertype();
		String[] jobRolearr = jobRolesJson.getJobRole();
		for (int i = 0; i < jobRolearr.length; i++) {
			for (int j = 0; j < usertType.length; i++) {
				UserType usertype = userTypeService.findByUserType(usertType[j]);
				List<Unit> unitlist = usertype.getUnit();
				jobRoleService.save(jobRolearr[i], usertype, unitlist, "TRUE");
			}
		}
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createJobTitle", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createJobTitle(@RequestBody JobTitleJson jobTitleJson) {

		String[] jobTitleArr = jobTitleJson.getJobTitle();
		Department department = departmentService.findbyName(jobTitleJson.getDepartment());
		if (null != department && department.getName() != null) {
			for (int i = 0; i < jobTitleArr.length; i++) {
				jobTitleService.save(jobTitleArr[i], department);
			}
		}

		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createSharedDocs", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createSharedPrivileges(@RequestBody SharedDocPrivilegeJson privilegeJson) {
		SharedDocPrivilege sharedDocPrivilege = null;
		if (privilegeJson != null) {
			String[] sharedPrivileges = privilegeJson.getSharedDocPrivileges();
			for (int i = 0; i < sharedPrivileges.length; i++) {
				sharedDocPrivilege = new SharedDocPrivilege();
				sharedDocPrivilege.setId(System.nanoTime());
				sharedDocPrivilege.setName(sharedPrivileges[i]);
				sharedDocPrivilegeService.save(sharedDocPrivilege);
			}
		}
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createSpecialJobRole", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createSpaecialJobRole(@RequestBody SpecialJobRoleJson specialJobRoleJson) {
		SpecialRole specialRole = null;
		if (specialJobRoleJson != null) {
			String name = specialJobRoleJson.getBaseLocation();
			BaseLocation baseLocation = baseLocationService.findByName(name);
			String[] specialrolename = specialJobRoleJson.getSpecialJobRole();
			String[] descarr = specialJobRoleJson.getRoleDescription();
			for (int i = 0; i < specialrolename.length; i++) {
				specialRole = new SpecialRole();
				specialRole.setId(System.nanoTime());
				specialRole.setBaseLocation(baseLocation);
				specialRole.setName(specialrolename[i]);
				specialRole.setSpecialJobRoleDesc(descarr[i]);
				specialRoleService.save(specialRole);
			}
		}

		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createApplication", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createApplication(@RequestBody ApplicationJson applicationJson) {
		String[] appl = applicationJson.getAppName();

		Application application = null;
		for (int i = 0; i < appl.length; i++) {
			application = new Application();
			application.setId(System.nanoTime());
			application.setName(appl[i]);
			applicationService.save(application);
		}
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createDeptDocPreveliges", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createDeptDocPreveliges(@RequestBody DeptDocPrivilegeJson deptDocPrivilegeJson) {
		String[] deptdocs = deptDocPrivilegeJson.getDeptdocname();
		String[] deptdocdesc = deptDocPrivilegeJson.getDeptdocdesc();
		DeptDocPrivilege deptDocPrivilege = null;
		for (int i = 0; i < deptdocs.length; i++) {
			deptDocPrivilege = new DeptDocPrivilege();
			deptDocPrivilege.setId(System.nanoTime());
			deptDocPrivilege.setName(deptdocs[i]);
			deptDocPrivilege.setDesc(deptdocdesc[i]);
			deptDocPrivilegeService.save(deptDocPrivilege);
		}
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createOtherDocPreveliges", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createOtherDocPreveliges(@RequestBody OtherDocPreveligesJson otherDocPreveliges) {
		String[] deptdocs = otherDocPreveliges.getOtherDocPreveligesname();
		String[] deptdocdesc = otherDocPreveliges.getOtherDocPreveligesdesc();
		OtherDocPrivilege otherDocPrivilege = null;
		for (int i = 0; i < deptdocs.length; i++) {
			otherDocPrivilege = new OtherDocPrivilege();
			otherDocPrivilege.setId(System.nanoTime());
			otherDocPrivilege.setName(deptdocs[i]);
			otherDocPrivilege.setDesc(deptdocdesc[i]);
			otherDocPrivilegeService.save(otherDocPrivilege);
		}
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createPermissionGroupMapping", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createPermissionGroupMapping(
			@RequestBody PermissionGroupMappingJson permissionGroupMappingJson) {
		String permissionId = permissionGroupMappingJson.getPermissiongroupname();
		long permissionlong = Long.parseLong(permissionId);
		PermissionGroup permissionGroup = permissionGroupService.findById(permissionlong);
		long[] application = permissionGroupMappingJson.getApplications();
		List<Application> appllist = new ArrayList<>();
		for (int i = 0; i < application.length; i++) {
			appllist.add(applicationService.findById(application[i]));
		}
		permissionGroup.setApplication(appllist);
		long deptdocprev = permissionGroupMappingJson.getDeptDocPrivilege();
		permissionGroup.setDeptDocPrivilege(deptDocPrivilegeService.findById(deptdocprev));
		long otherdocprev = permissionGroupMappingJson.getOtherDocPrivilege();
		permissionGroup.setOtherDocPrivilege(otherDocPrivilegeService.findById(otherdocprev));
		long sharedDocPrivilege = permissionGroupMappingJson.getSharedDocPrivilege();
		permissionGroup.setSharedDocPrivilege(sharedDocPrivilegeService.findById(sharedDocPrivilege));
		permissionGroupService.save(permissionGroup);
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createSpecialRoleMapping", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createSpecialRoleMapping(@RequestBody SpecialRoleMappingJson specialRoleMappingJson) {
		SpecialRole specialRole = specialRoleService.findById(specialRoleMappingJson.getSpecialRoleId());
		String emplistFlag = specialRoleMappingJson.getEmplistFlag();
		List<PermissionGroup> permissionGroupList = new ArrayList<>();
		List<Application> applicationlst = new ArrayList<>();

		long[] permissiongrouparr = specialRoleMappingJson.getPermissionGroup();
		String location = null;
		if (null != specialRole) {
			if (emplistFlag.equals("TRUE")) {
				specialRole.setSpecialRoleFlag(emplistFlag);
				specialRoleService.save(specialRole);
			}
		} else {
			specialRole = specialRoleService.createSpecialRole(location, specialRoleMappingJson.getSpecialRoleName(),
					emplistFlag, specialRoleMappingJson.getSpecialRoleDesc());
		}
		SpecialRoleMapping specialRoleMapping = specialRoleMappingService.findBySpecialRole(specialRole);
		if (null != specialRoleMapping) {
			long[] applications = specialRoleMappingJson.getApplication();
			for (int i = 0; i < applications.length; i++) {
				Application application = applicationService.findById(applications[i]);
				applicationlst.add(application);
			}
			if (null != permissiongrouparr) {
				for (int i = 0; i < permissiongrouparr.length; i++) {
					PermissionGroup permissionGroup = permissionGroupService.findById(permissiongrouparr[i]);
					permissionGroupList.add(permissionGroup);
				}
			}
			specialRoleMapping.setPermissionGroup(permissionGroupList);
			specialRoleMapping.setApplication(applicationlst);
			specialRoleMapping.setEgnyteFolder(specialRoleMappingJson.getEgnytePath());
			specialRoleMapping.setSpecialPermissionGroup(specialRoleMappingJson.getSpecialPermissionGroup());
			specialRoleMapping.setSpecialPrivilege(specialRoleMappingJson.getSpecialPrivileges());
			specialRoleMappingService.save(specialRoleMapping);
		} else {
			specialRoleMapping = new SpecialRoleMapping();
			specialRoleMapping.setId(System.nanoTime());
			long[] applications = specialRoleMappingJson.getApplication();
			for (int i = 0; i < applications.length; i++) {
				Application application = applicationService.findById(applications[i]);
				applicationlst.add(application);
			}
			if (null != permissiongrouparr) {
				for (int i = 0; i < permissiongrouparr.length; i++) {
					PermissionGroup permissionGroup = permissionGroupService.findById(permissiongrouparr[i]);
					permissionGroupList.add(permissionGroup);
				}
			}
			specialRoleMapping.setSpecialRole(specialRole);
			specialRoleMapping.setPermissionGroup(permissionGroupList);
			specialRoleMapping.setApplication(applicationlst);
			specialRoleMapping.setEgnyteFolder(specialRoleMappingJson.getEgnytePath());
			specialRoleMapping.setSpecialPermissionGroup(specialRoleMappingJson.getSpecialPermissionGroup());
			specialRoleMapping.setSpecialPrivilege(specialRoleMappingJson.getSpecialPrivileges());
			specialRoleMappingService.save(specialRoleMapping);

		}
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/createAdditionalLocation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createAdditionalLocation(@RequestBody AdditionalLocationJson additionalLocationJson) {

		String[] additionallocation = additionalLocationJson.getAdditionallocation();
		String[] additionallocationdesc = additionalLocationJson.getAdditionallocationDesc();
		for (int i = 0; i < additionallocation.length; i++) {
			AdditionalLocation additionalLocation = new AdditionalLocation();
			additionalLocation.setId(System.nanoTime());
			additionalLocation.setName(additionallocation[i]);
			additionalLocation.setDesc(additionallocationdesc[i]);
			additionalLocationService.save(additionalLocation);
		}
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/processFullInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity processAllInputData(@RequestBody AllInfoJson allInfoJson) {
		DistributionGroupController distributionGroupController = new DistributionGroupController();
		SharedDocPrivilegeJson sharedDocPrivilegeJson = allInfoJson.getSharedDocPrivilegeJson();
		AdditionalLocationJson additionalLocationJson = allInfoJson.getAdditionalLocationJson();
		OtherDocPreveligesJson otherDocPreveligesJson = allInfoJson.getOtherDocPreveligesJson();
		DeptDocPrivilegeJson deptDocPrivilegeJson = allInfoJson.getDeptDocPrivilegeJson();
		SpecialJobRoleJson specialJobRoleJson = allInfoJson.getSpecialJobRoleJson();
		ApplicationJson applicationJson = allInfoJson.getApplications();
		for (UnitDepartmentJson unitDepartmentJson : allInfoJson.getUnitsDepartments()) {
			saveDepartmentInfo(unitDepartmentJson);
		}
		for (UserTypeJson userTypeJson : allInfoJson.getUnitUserTypes()) {
			saveUsertypeInfo(userTypeJson);
		}
		for (JobRoleJson jobRoleJson : allInfoJson.getJobRoles()) {
			saveJobRoleInfo(jobRoleJson);
		}
		for (XternalListJson xternalListJson : allInfoJson.getXternalListJson()) {
			createXternalList(xternalListJson);
		}
		for (JobTitleJson jobTitleJson : allInfoJson.getJobTitles()) {
			createJobTitle(jobTitleJson);
		}
		createApplication(applicationJson);
		createDeptDocPreveliges(deptDocPrivilegeJson);
		createSpaecialJobRole(specialJobRoleJson);
		createSharedPrivileges(sharedDocPrivilegeJson);
		createAdditionalLocation(additionalLocationJson);
		createOtherDocPreveliges(otherDocPreveligesJson);
		createPermissionGroup();
		createDistributionGroup();
		createDailyDistributionGroup();
		createCallSheet();

		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createDistributionGroup", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createDistributionGroup() {
		distributionGroupService.deleteAll();
		List<BaseLocation> baselocationList = baseLocationService.findAll();
		List<Unit> unitlist = unitService.findAll();
		List<String> location = new ArrayList<>();
		List<BaseLocation> baseLocationlst = baseLocationService.findAll();
		for (BaseLocation locationobj : baseLocationlst) {
			location.add(locationobj.getName());
		}
		int count = 0;
		List<Department> departmentlist = null;
		List<UserType> usertypelist = null;

		for (Unit unit : unitlist) {
			departmentlist = departmentService.findByUnit(unit);
			for (Department department : departmentlist) {
				DistributionGroup distributionGroup = new DistributionGroup();
				distributionGroup.setId(System.nanoTime());
				distributionGroup.setLocation(location);
				distributionGroup.setUnit(unit);
				distributionGroup.setDepartment(department);
				distributionGroup.setDefaultvalue("true");
				usertypelist = userTypeService.findByUnit(unit);
				distributionGroup.setUserType(usertypelist);
				for (UserType userType : usertypelist) {
					List<JobRole> jobrolelist = userType.getJobRoleList();
					for (JobRole jobRole : jobrolelist) {
						if (!((jobRole.getName().equals("Daily")) || (department.getName().equals("Xternal")))) {

							distributionGroup.setJobRole(userType.getJobRoleList());
							StringBuilder distributionGroupname = new StringBuilder();
							distributionGroupname.append(distroname);
							if (deptflag.equals("TRUE")) {
								distributionGroupname.append(department.getName()).append("-Dept-");
							}
							if (unitflag.equals("TRUE")) {
								distributionGroupname.append(unit.getName());
							}
							if (blocationFlag.equals("TRUE")) {
								distributionGroupname.append(".").append(baselocationList.get(0).getName());
							}
							log.info("%%%%%%%%%%%%%%" + distributionGroupname, "distro group name");
							distributionGroup.setName(distributionGroupname.toString());
							distributionGroupService.save(distributionGroup);
							count++;
						}
					}

				}
			}
			log.info("%%%%%%%%%%%%%%" + count, "no of records ");
		}
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createDailyDistributionGroup", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createDailyDistributionGroup() {
		dailyDistributionGroupService.deleteAll();
		List<BaseLocation> baselocationList = baseLocationService.findAll();
		List<Unit> unitlist = unitService.findAll();
		List<String> location = new ArrayList<>();
		List<BaseLocation> baseLocationlst = baseLocationService.findAll();
		for (BaseLocation locationobj : baseLocationlst) {
			location.add(locationobj.getName());
		}
		int count = 0;
		List<Department> departmentlist = null;
		List<UserType> usertypelist = null;
		for (Unit unit : unitlist) {
			departmentlist = departmentService.findByUnit(unit);
			usertypelist = userTypeService.findByUnit(unit);
			for (Department department : departmentlist) {
				if (!(department.getName().equals("Xternal"))) {
					for (UserType userType : usertypelist) {
						List<JobRole> jobrolelist = userType.getJobRoleList();
						for (JobRole jobRole : jobrolelist) {
							if (jobRole.getName().equals("Daily")) {
								DailyDistributionGroup dailyDistributionGroup = new DailyDistributionGroup();
								dailyDistributionGroup.setLocation(location);
								dailyDistributionGroup.setUnit(unit);
								dailyDistributionGroup.setId(System.nanoTime());
								dailyDistributionGroup.setDepartment(department);
								dailyDistributionGroup.setUserType(usertypelist);
								dailyDistributionGroup.setDefaultvalue("true");
								List<JobRole> dailyjob = new ArrayList<>();
								dailyjob.add(jobRole);
								dailyDistributionGroup.setJobRole(dailyjob);
								StringBuilder distributionGroupname = new StringBuilder();
								distributionGroupname.append(distroname);
								if (deptflag.equals("TRUE")) {
									distributionGroupname.append(department.getName()).append("-Dept-");
								}
								if (unitflag.equals("TRUE")) {
									distributionGroupname.append(unit.getName()).append(dailydistroname);
								}
								if (blocationFlag.equals("TRUE")) {
									distributionGroupname.append(".").append(baselocationList.get(0).getName());
								}
								// distributionGroupname.append("Distro-").append(department.getName()).append("-DAILYHIRES-").
								// append(unit.getName()).append(".").append(baselocationList.get(0).getName()).append("-")
								// .append(jobRole.getName());
								count++;
								dailyDistributionGroup.setName(distributionGroupname.toString());
								log.info("Daily distron group name %%%%%%%%%%%%%%" + distributionGroupname + "%%%%");
								dailyDistributionGroup.setName(distributionGroupname.toString());

								dailyDistributionGroupService.save(dailyDistributionGroup);
							}
						}
					}
				}
			}
			log.info("%%%%%%%%%%----%%%%%%%" + count, "no of records ");
		}
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createCallSheet", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createCallSheet() {
		List<BaseLocation> baselocationList = baseLocationService.findAll();
		List<Unit> unitlist = unitService.findAll();
		List<String> location = new ArrayList<>();
		List<BaseLocation> baseLocationlst = baseLocationService.findAll();
		for (BaseLocation locationobj : baseLocationlst) {
			location.add(locationobj.getName());
		}

		List<Department> departmentlist = null;

		int count = 0;

		callSheetService.deleteAll();
		for (Unit unit : unitlist) {
			CallSheet callSheet = new CallSheet();
			callSheet.setLocation(location);
			callSheet.setUnit(unit);
			callSheet.setId(System.nanoTime());
			callSheet.setDepartment(departmentService.findByUnit(unit));
			callSheet.setUserType(userTypeService.findByUnit(unit));
			callSheet.setDefaultvalue("true");
			departmentlist = departmentService.findByUnit(unit);
			for (Department department : departmentlist) {
				for (UserType userType : userTypeService.findByUnit(unit)) {
					List<JobRole> jobrolelist = userType.getJobRoleList();
					for (JobRole jobRole : jobrolelist) {
						if (((jobRole.getName().equals("Daily")) || (department.getName().equals("Xternal")))) {
							callSheet.setJobRole(userType.getJobRoleList());
							StringBuilder distributionGroupname = new StringBuilder();
							distributionGroupname.append("Distro-CallSheet").append(department.getName()).append("")
									.append(unit.getName()).append(".").append(baselocationList.get(0).getName());

							log.info("%%%%%%%%%%%%%%----%%%%%%%%" + distributionGroupname, "call sheet name");

							callSheet.setName(distributionGroupname.toString());
							count++;
							callSheetService.save(callSheet);
						}
					}
				}
			}

		}
		log.info("%%%%%%%%%%%%%%" + count, "call sheet count");
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createDistributionListMapping", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createDistributionListMapping(
			@RequestBody DistributionGroupMappingJson distributionGroupMappingJson) {
		long[] deptlst = distributionGroupMappingJson.getDepartment();
		long[] unitlst = distributionGroupMappingJson.getUnit();
		long[] userType = distributionGroupMappingJson.getUsertype();
		long[] jobRolelst = distributionGroupMappingJson.getJobrole();
		String[] locationlst = distributionGroupMappingJson.getLocation();
		String defaultvalue = distributionGroupMappingJson.getDefaultvalue();
		List<Department> departmentlst = new ArrayList<>();
		List<String> locationlist = new ArrayList<>();
		List<UserType> userTypelst = new ArrayList<>();
		List<Unit> unitlist = new ArrayList<>();
		List<JobRole> jobRoleslst = new ArrayList<>();

		for (int i = 0; i < locationlst.length; i++) {
			locationlist.add(locationlst[i]);
		}

		for (int i = 0; i < userType.length; i++) {
			UserType userTypeobj = userTypeService.findById(userType[i]);
			userTypelst.add(userTypeobj);
		}

		for (int i = 0; i < jobRolelst.length; i++) {
			JobRole jobRoleObj = jobRoleService.findById(jobRolelst[i]);
			jobRoleslst.add(jobRoleObj);
		}

		for (int i = 0; i < deptlst.length; i++) {
			Department department = departmentService.findById(deptlst[i]);
			departmentlst.add(department);
		}

		for (int i = 0; i < unitlst.length; i++) {
			Unit unit = unitService.findbyId(unitlst[i]);
			unitlist.add(unit);
		}
		if (defaultvalue.equals("false")) {
			distributionGroupService.createDistributionGroup(defaultvalue, locationlist,
					distributionGroupMappingJson.getDistributionGroup(), unitlist, departmentlst, userTypelst,
					jobRoleslst);
		}
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createEmpDistributionListMapping", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createEmpDistributionListMapping(
			@RequestBody DistributionGroupMappingJson distributionGroupMappingJson) {

		String[] locations = distributionGroupMappingJson.getLocation();
		String distroGroupName = distributionGroupMappingJson.getDistributionGroup();
		DistributionGroup distributionGroup = distributionGroupService.findByName(distroGroupName);
		BaseLocation baseLocation = null;
		List<EmpDetail> empDetailList = null;
		if (locations != null) {
			for (String location : locations) {
				baseLocation = baseLocationService.findByName(location);
				if (baseLocation != null) {
					empDetailList = employeeService.findByBaseLocation(baseLocation);
					if (empDetailList != null) {
						for (EmpDetail empDetail : empDetailList) {
							if (empDetail.getDistributionGroup() != null) {
								// empDetail.getDistributionGroup().add(distributionGroup);
							}
						}
					}
				}
			}
		}
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/checkduplicatedata/{type}/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity processAllInputData(@PathVariable("type") String type, @PathVariable("name") String name) {
		BaseResponse response = null;
		Unit unit = null;
		DistributionGroup distributionGroup = null;
		Department department = null;
		UserType userType = null;
		JobRole jobRole = null;
		SpecialRole specialRole = null;
		Application application = null;
		if (type.equals("unit")) {
			unit = unitService.findbyName(name);
			if (null != unit && unit.getName().equalsIgnoreCase(name)) {
				response = new BaseResponse("100", "Unit list already exists");

			}
		}
		if (type.equals("department")) {
			department = departmentService.findbyName(name);
			if (null != department && department.getName().equalsIgnoreCase(name)) {
				response = new BaseResponse("100", "Department Name already exists");

			}
		}
		if (type.equals("usertype")) {
			userType = userTypeService.findByUserType(name);
			if (null != userType && userType.getName().equalsIgnoreCase(name)) {
				response = new BaseResponse("100", "Usertype already exists");

			}
		}
		if (type.equals("jobrole")) {
			jobRole = jobRoleService.findByName(name);
			if (null != jobRole && jobRole.getName().equalsIgnoreCase(name)) {
				response = new BaseResponse("100", "Job role already exists");

			}
		}
		if (type.equals("specialrole")) {
			specialRole = specialRoleService.findByName(name);
			if (null != specialRole && specialRole.getName().equalsIgnoreCase(name)) {
				response = new BaseResponse("100", "Special role already exists");

			}
		}
		if (type.equals("application")) {
			application = applicationService.findByName(name);
			if (null != application && application.getName().equalsIgnoreCase(name)) {
				response = new BaseResponse("100", "Application already exists");

			}
		}
		if (type.equals("distributiongroup")) {
			distributionGroup = distributionGroupService.findByName(name);
			if (null != distributionGroup && distributionGroup.getName().equalsIgnoreCase(name)) {
				response = new BaseResponse("100", "Distribution Group already exists");

			}
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/createXternalList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createXternalList(@RequestBody XternalListJson xternalListJson) {
		String xunit = "xternal";
		String xdepartment = xternalListJson.getXdepartment();
		String xuserType = xternalListJson.getXuserType();
		String xjobRole = xternalListJson.getXjobrole();
		List<JobRole> jobroleList = new ArrayList<>();
		List<Unit> unitlist = new ArrayList<>();
		Unit unit = unitService.findbyName(xunit);
		Department department = departmentService.findbyName(xdepartment);

		if (null == unit) {
			unit = new Unit();
			unit.setId(System.nanoTime());
			unit.setName(xunit);
			unitService.save(unit);
			unitlist.add(unit);

			if (null != department) {
				department = departmentService.update(department, xdepartment, unit);
			} else {
				department = new Department();
				department.setId(System.nanoTime());
				department.setName(xdepartment);
				department.setUnit(unitlist);
				department = departmentService.save(xdepartment, unitlist);
			}
		} else {
			unitlist.add(unit);
		}

		UserType userType = userTypeService.findByUserType(xuserType);
		JobRole jobRole = jobRoleService.save(xjobRole,userType,unitlist,"TRUE");
		jobroleList.add(jobRole);
		if (null != userType) {
			System.out.println("jobrole::::size:::" + jobroleList.get(0).getName());
		
		} else {
			System.out.println("jobrole::::size:::" + jobroleList.get(0).getName());
			userType = userTypeService.saveXternal(xuserType, unitlist, jobroleList);

		}

		XternalDistributionGroup xterlDistributionGroup = new XternalDistributionGroup();
		xterlDistributionGroup.setId(System.nanoTime());
		StringBuilder xternalName = new StringBuilder();
		xternalName.append("Distro-Xternal").append(xjobRole);
		xterlDistributionGroup.setName(xternalName.toString());
		xterlDistributionGroup.setDepartment(department);
		xterlDistributionGroup.setUserType(userType);
		xterlDistributionGroup.setJobRole(jobRole);
		xterlDistributionGroupService.save(xterlDistributionGroup);
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
