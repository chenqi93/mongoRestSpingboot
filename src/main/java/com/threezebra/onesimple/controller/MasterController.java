package com.threezebra.onesimple.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.threezebra.domain.Application;
import com.threezebra.domain.BaseLocation;
import com.threezebra.domain.BaseResponse;
import com.threezebra.domain.Department;
import com.threezebra.domain.DeptDocPrivilege;
import com.threezebra.domain.DeviceInfo;
import com.threezebra.domain.DistributionGroup;
import com.threezebra.domain.DistributionGroupMapping;
import com.threezebra.domain.JobRole;
import com.threezebra.domain.OtherDocPrivilege;
import com.threezebra.domain.PermissionGroup;
import com.threezebra.domain.PermissionGroupMapping;
import com.threezebra.domain.SharedDocPrivilege;
import com.threezebra.domain.SpecialRole;
import com.threezebra.domain.SpecialRoleMapping;
import com.threezebra.domain.Unit;
import com.threezebra.domain.UserType;
import com.threezebra.domain.XternalDistributionGroup;
import com.threezebra.onesimple.dto.ApplicationJson;
import com.threezebra.onesimple.dto.DeptDocPrivilegeJson;
import com.threezebra.onesimple.dto.DeviceInfoJson;
import com.threezebra.onesimple.dto.DistributionGroupMappingJson;
import com.threezebra.onesimple.dto.JobRoleJson;
import com.threezebra.onesimple.dto.OtherDocPreveligesJson;
import com.threezebra.onesimple.dto.PermissionGroupMappingJson;
import com.threezebra.onesimple.dto.SharedDocPrivilegeJson;
import com.threezebra.onesimple.dto.SpecialJobRoleJson;
import com.threezebra.onesimple.dto.SpecialRoleMappingJson;
import com.threezebra.onesimple.dto.UnitDepartmentJson;
import com.threezebra.onesimple.dto.UserTypeJson;
import com.threezebra.onesimple.dto.XternalListJson;
import com.threezebra.restapi.dto.JobTitleJson;
import com.threezebra.service.AdditionalLocationService;
import com.threezebra.service.ApplicationService;
import com.threezebra.service.BaseLocationService;
import com.threezebra.service.DepartmentService;
import com.threezebra.service.DeptDocPrivilegeService;
import com.threezebra.service.DeviceInfoService;
import com.threezebra.service.DistributionGroupMappingService;
import com.threezebra.service.DistributionGroupService;
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
	DistributionGroupService distributionGroupService;
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
	XterlDistributionGroupService xterlDistributionGroupService;
	Long count = System.currentTimeMillis();
	private List<UserType> usertypelist;

	@RequestMapping(value = "/createUnitandDepartment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity saveDepartmentInfo(@RequestBody UnitDepartmentJson unitDepartmentJson) {
		List<Unit> unitlist = new ArrayList<>();
		BaseLocation baselocation = baseLocationService.save(unitDepartmentJson.getBaseLocation(), count);
		String[] unitarr = unitDepartmentJson.getUnit();
		for (int i = 0; i < unitarr.length; i++) {
			unitlist.add(unitService.update(unitarr[i], baselocation));

		}
		String[] deptarr = unitDepartmentJson.getDepartment();
		for (int i = 0; i < deptarr.length; i++) {
			departmentService.update(deptarr[i], unitlist);
		}
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createUserType", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity saveUsertypeInfo(@RequestBody UserTypeJson userTypeJson) {
		List<Unit> unitlist = new ArrayList<>();
		String[] unitarr = userTypeJson.getUnit();
		for (int i = 0; i < unitarr.length; i++) {
			unitlist.add(unitService.findbyName(unitarr[i]));
		}
		String[] userTypearr = userTypeJson.getUsertype();
		List<String> useruntnamelst = new ArrayList<>();
		for (int i = 0; i < userTypearr.length; i++) {
			UserType userType = userTypeService.findByUserType(userTypearr[i]);

			if (null != userType) {
				List<Unit> userunitlst = userType.getUnit();
				for (Unit unit : userunitlst) {
					useruntnamelst.add(unit.getName());
				}
				for (Unit unit : unitlist) {
					if (!(useruntnamelst.contains(unit.getName()))) {
						userTypeService.update(userType, unit);
					}
				}
			} else {
				userTypeService.save(userTypearr[i], unitlist);
			}
		}

		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createDistributionGroup", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createDistributionGroup() {
		List<BaseLocation> baselocationList = baseLocationService.findAll();
		List<Unit> unitlist = unitService.findAll();
		List<Department> departmentlist = null;
		List<UserType> usertypelist = null;
		List<JobRole> jobRoleList = null;
		distributionGroupService.deleteAll();
		for (Unit unit : unitlist) {
			departmentlist = departmentService.findByUnit(unit);
			for (Department department : departmentlist) {
			
				usertypelist = userTypeService.findByUnit(unit);
				if (null != usertypelist) {
					for (UserType userType : usertypelist) {
						jobRoleList = jobRoleService.findByUserType(userType);
					}
				}
				if (null != jobRoleList) {
					for (JobRole jobRole : jobRoleList) {
						if (!(jobRole.getName().contains("InfoRecipient") || jobRole.getName().contains("Daily"))) {
							DistributionGroup distributionGroup = new DistributionGroup();
							StringBuilder distributionGroupname = new StringBuilder();
							distributionGroup.setId(System.nanoTime());
							distributionGroup.setUnit(unit);
							distributionGroup.setJobRole(jobRole);
							distributionGroup.setDepartment(department);
							distributionGroupname.append("Distro.").append(department.getName()).append("-Dept-").append(unit.getName()).append(".").append(baselocationList.get(0).getName());
							System.out.println("%%%%%%%%%%%%%%" + distributionGroupname + "." + jobRole.getName()
									+ "%%%%%%%%%%%%%%");
							distributionGroup.setName(distributionGroupname.toString());
							distributionGroupService.save(distributionGroup);
						}

					}

				}
			}
		
		}
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@RequestMapping(value = "/createXternalList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createXternalList(@RequestBody XternalListJson xternalListJson) {
		String xdeparment= xternalListJson.getXdepartment();
		String xuserType=xternalListJson.getXuserType();
		String xjobRole=xternalListJson.getXjobrole();
		Department department=departmentService.findbyName(xdeparment);
		if(null!=department) {
			department=departmentService.update(xdeparment, null);
		}
		else
		{ department=new Department();
		   department.setId(System.nanoTime());
		   department.setName(xdeparment);
		   department.setUnit(null);
		   department=departmentService.save(department);
		}
		 
		UserType userType=userTypeService.findByUserType(xuserType);
		if(null!=userType) {
			userType.setName(xdeparment);
			userType=userTypeService.update(userType, null);
		}
		else
		{ userType=new UserType();
		userType=userTypeService.save(xuserType,null);
		}
		
		 JobRole jobRole=jobRoleService.update(userType,xjobRole);
		XternalDistributionGroup xterlDistributionGroup=new XternalDistributionGroup();
		xterlDistributionGroup.setId(System.nanoTime());
		StringBuilder xternalName=new StringBuilder();
		xternalName.append("Distro-Xternal").append(xjobRole);
		xterlDistributionGroup.setName(xternalName.toString());
		xterlDistributionGroup.setDepartment(department);
		xterlDistributionGroup.setUserType(userType);
		xterlDistributionGroup.setJobRole(jobRole);
		xterlDistributionGroupService.save(xterlDistributionGroup);
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

		for (Unit unit : unitlist) {
			departmentlist = departmentService.findByUnit(unit);
			List<UserType> userTypelst = userTypeService.findByUnit(unit);
			if (userTypelst.size() > 0) {
				usertypelist = userTypelst;
			}
			if (null != usertypelist) {
				for (UserType userType : usertypelist) {
					jobRoleList = jobRoleService.findByUserType(userType);
				}
			}
			for (Department department : departmentlist) {
				for (JobRole jobRole : jobRoleList) {
					if (!(jobRole.getName().contains("InfoRecipient") || jobRole.getName().contains("Daily"))) {

						List<BaseLocation> baselocationlist = baseLocationService.findAll();
						for (BaseLocation baseLocation : baselocationlist) {
							PermissionGroup permissionGroup = new PermissionGroup();
							StringBuilder permissiongroupname = new StringBuilder();
							permissionGroup.setId(System.nanoTime());
							permissionGroup.setUnit(unit.getName());
							permissionGroup.setJobRole(jobRole.getName());
							permissionGroup.setDepartment(department.getName());
							permissiongroupname.append(baseLocation.getName()).append(".").append(unit.getName())
									.append(".").append(department.getName()).append(".").append(jobRole.getName());
							permissionGroup.setName(permissiongroupname.toString());
							permissionGroupService.save(permissionGroup);
						}
						count++;
					}

				}

			}
		}

	

	BaseResponse response = new BaseResponse("200", "SUCCESS");
	return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@RequestMapping(value = "/createJobRole", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity saveUsertypeInfo(@RequestBody JobRoleJson jobRoleJson) {
		UserType usertype = null;
		String[] userTypearr = jobRoleJson.getUsertype();
		for (int i = 0; i < userTypearr.length; i++) {
			usertype = userTypeService.findByUserType(userTypearr[i]);
		}

		String[] jobRolearr = jobRoleJson.getJobRole();
		for (int i = 0; i < jobRolearr.length; i++) {
			jobRoleService.update(usertype, jobRolearr[i]);

		}
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createJobTitle", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createJobTitle(@RequestBody JobTitleJson jobTitleJson) {
		String[] jobTitleArr = jobTitleJson.getJobTitle();
		Department department = departmentService.findbyName(jobTitleJson.getDepartment());
		if(null!=department) {
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

	@RequestMapping(value = "/createDeviceInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createDeviceInfo(@RequestBody DeviceInfoJson deviceInfoJson) {
		String[] deviceno = deviceInfoJson.getDeviceno();
		String[] devicename = deviceInfoJson.getDevicename();
		DeviceInfo deviceInfo = null;
		for (int i = 0; i < deviceno.length; i++) {
			deviceInfo = new DeviceInfo();
			deviceInfo.setId(System.nanoTime());
			deviceInfo.setDeviceno(deviceno[i]);
			deviceInfo.setName(devicename[i]);
			deviceInfoService.save(deviceInfo);
		}
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createPermissionGroupMapping", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createPermissionGroupMapping(
			@RequestBody PermissionGroupMappingJson permissionGroupMappingJson) {
		String permissionId = permissionGroupMappingJson.getPermissiongroupname();
		long permissionlong=Long.parseLong(permissionId);
		PermissionGroup permissionGroup = permissionGroupService.findById(permissionlong);
		PermissionGroupMapping permissionGroupMappingobj = permissionGroupMappingService
				.findByPermissionGroup(permissionGroup);
		if (null != permissionGroupMappingobj) {
			permissionGroupMappingobj.setPermissionGroup(permissionGroup);
			long[] application = permissionGroupMappingJson.getApplications();
			List<Application> appllist = new ArrayList<>();
			for (int i = 0; i < application.length; i++) {
				appllist.add(applicationService.findById(application[i]));
			}
			permissionGroupMappingobj.setApplications(appllist);
			long deptdocprev = permissionGroupMappingJson.getDeptDocPrivilege();
			permissionGroupMappingobj.setDeptDocPrivileges(deptDocPrivilegeService.findById(deptdocprev));
			long otherdocprev = permissionGroupMappingJson.getOtherDocPrivilege();
			permissionGroupMappingobj.setOtherDocPrivilege(otherDocPrivilegeService.findById(otherdocprev));
			long sharedDocPrivilege = permissionGroupMappingJson.getSharedDocPrivilege();
			permissionGroupMappingobj.setSharedDocPrivilege(sharedDocPrivilegeService.findById(sharedDocPrivilege));
			permissionGroupMappingService.save(permissionGroupMappingobj);

		} else {
			PermissionGroupMapping permissionGroupMapping = new PermissionGroupMapping();
			permissionGroupMapping.setId(System.nanoTime());
			permissionGroupMapping.setPermissionGroup(permissionGroup);
			long[] application = permissionGroupMappingJson.getApplications();
			List<Application> appllist = new ArrayList<>();
			for (int i = 0; i < application.length; i++) {
				appllist.add(applicationService.findById(application[i]));
			}
			permissionGroupMapping.setApplications(appllist);
			long deptdocprev = permissionGroupMappingJson.getDeptDocPrivilege();
			permissionGroupMapping.setDeptDocPrivileges(deptDocPrivilegeService.findById(deptdocprev));
			long otherdocprev = permissionGroupMappingJson.getOtherDocPrivilege();
			permissionGroupMapping.setOtherDocPrivilege(otherDocPrivilegeService.findById(otherdocprev));
			long sharedDocPrivilege = permissionGroupMappingJson.getSharedDocPrivilege();
			permissionGroupMapping.setSharedDocPrivilege(sharedDocPrivilegeService.findById(sharedDocPrivilege));
			permissionGroupMappingService.save(permissionGroupMapping);
		}

		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createDistributionListMapping", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createDistributionListMapping(
			@RequestBody DistributionGroupMappingJson distributionGroupMappingJson) {
		DistributionGroup distributionGroup = distributionGroupService
				.findByName(distributionGroupMappingJson.getDistributionGroup());
		long[] deptlst = distributionGroupMappingJson.getDepartment();
		long[] unitlst = distributionGroupMappingJson.getUnit();
		long[] jobRolelst = distributionGroupMappingJson.getJobrole();
		if(null!=distributionGroup) {
			distributionGroup=distributionGroupService.createDistributionGroup(distributionGroupMappingJson.getDistributionGroup(),unitlst[0],deptlst[0],distributionGroupMappingJson.getUsertype(),jobRolelst[0]);
		}
		else {
		DistributionGroupMapping distributionGroupMapping = distributionGroupMappingService
				.findBydistributionGroup(distributionGroup);
		List<Department> departmentlst = new ArrayList<>();
		List<Unit> untlst = new ArrayList<>();
		List<JobRole> jobRoleslst = new ArrayList<>();
		if (null != distributionGroupMapping) {
			
			for (int i = 0; i < deptlst.length; i++) {
				Department department = departmentService.findById(deptlst[i]);
				departmentlst.add(department);
			}
			distributionGroupMapping.setDepartment(departmentlst);
			
			for (int i = 0; i < unitlst.length; i++) {
				Unit unit = unitService.findbyId(unitlst[i]);
				untlst.add(unit);
			}
			distributionGroupMapping.setUnit(untlst);
		
			for (int i = 0; i < jobRolelst.length; i++) {
				JobRole jobRole = jobRoleService.findById(jobRolelst[i]);
				jobRoleslst.add(jobRole);
			}
			distributionGroupMapping.setJobrole(jobRoleslst);
			UserType userType = userTypeService.findById(distributionGroupMappingJson.getUsertype());
			distributionGroupMapping.setUsertype(userType);
			distributionGroupMappingService.save(distributionGroupMapping);

		} else {
			DistributionGroupMapping distributionGroupMappingObj = new DistributionGroupMapping();
			DistributionGroup distributionGroupObj = distributionGroupService
					.findByName(distributionGroupMappingJson.getDistributionGroup());
			distributionGroupMappingObj.setDistributionGroup(distributionGroupObj);
			distributionGroupMappingObj.setId(System.nanoTime());
			for (int i = 0; i < deptlst.length; i++) {
				Department department = departmentService.findById(deptlst[i]);
				departmentlst.add(department);
			}
			distributionGroupMappingObj.setDepartment(departmentlst);
			for (int i = 0; i < unitlst.length; i++) {
				Unit unit = unitService.findbyId(unitlst[i]);
				untlst.add(unit);
			}
			distributionGroupMappingObj.setUnit(untlst);
			
			for (int i = 0; i < jobRolelst.length; i++) {
				JobRole jobRole = jobRoleService.findById(jobRolelst[i]);
				jobRoleslst.add(jobRole);
			}
			distributionGroupMappingObj.setJobrole(jobRoleslst);
			UserType userType = userTypeService.findById(distributionGroupMappingJson.getUsertype());
			distributionGroupMappingObj.setUsertype(userType);
			distributionGroupMappingService.save(distributionGroupMappingObj);
		}
		}
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/createSpecialRoleMapping", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createSpecialRoleMapping(@RequestBody SpecialRoleMappingJson  specialRoleMappingJson) {
		SpecialRole specialRole=specialRoleService.findById(specialRoleMappingJson.getSpecialRoleId());
		String emplistFlag=specialRoleMappingJson.getEmplistFlag();
		List<PermissionGroup> permissionGroupList=new ArrayList<>();
		List<Application> applicationlst=new ArrayList<>();
	
		long[] permissiongrouparr=specialRoleMappingJson.getPermissionGroup();
		String location=null;
		if(null!=specialRole) {
		   if(emplistFlag.equals("TRUE") ) {
			specialRole.setSpecialRoleFlag(emplistFlag);
		   }
		 }
		else {
			specialRole=specialRoleService.createSpecialRole(location,specialRoleMappingJson.getSpecialRoleName(),emplistFlag,specialRoleMappingJson.getSpecialRoleDesc());
		}
		SpecialRoleMapping specialRoleMapping=specialRoleMappingService.findBySpecialRole(specialRole);
		if(null!=specialRoleMapping) {
			long[] applications=specialRoleMappingJson.getApplication();
			for(int i=0;i<applications.length;i++) {
				Application application=applicationService.findById(applications[i]);
				applicationlst.add(application);  
			}
			
			for(int i=0;i<permissiongrouparr.length;i++) {
			PermissionGroup permissionGroup=permissionGroupService.findById(permissiongrouparr[i]);
			permissionGroupList.add(permissionGroup);
			}
			specialRoleMapping.setPermissionGroup(permissionGroupList);
			
			specialRoleMapping.setApplication(applicationlst);
	        specialRoleMapping.setEgnyteFolder(specialRoleMappingJson.getEgnytePath());;
			specialRoleMapping.setSpecialPermissionGroup(specialRoleMappingJson.getSpecialPermissionGroup());
			specialRoleMapping.setSpecialPrivilege(specialRoleMappingJson.getSpecialPrivileges());
			specialRoleMappingService.save(specialRoleMapping);
		}
		else {
			specialRoleMapping=new SpecialRoleMapping();
			specialRoleMapping.setId(System.nanoTime());
			long[] applications = specialRoleMappingJson.getApplication();
			for (int i = 0; i < applications.length; i++) {
				Application application = applicationService.findById(applications[i]);
				applicationlst.add(application);
			}
			for(int i=0;i<permissiongrouparr.length;i++) {
				PermissionGroup permissionGroup=permissionGroupService.findById(permissiongrouparr[i]);
				permissionGroupList.add(permissionGroup);
				}
			specialRoleMapping.setPermissionGroup(permissionGroupList);
			specialRoleMapping.setApplication(applicationlst);
			specialRoleMapping.setEgnyteFolder(specialRoleMappingJson.getEgnytePath());;
			specialRoleMapping.setSpecialPermissionGroup(specialRoleMappingJson.getSpecialPermissionGroup());
			specialRoleMapping.setSpecialPrivilege(specialRoleMappingJson.getSpecialPrivileges());
			specialRoleMappingService.save(specialRoleMapping);

		}
		BaseResponse response = new BaseResponse("200", "SUCCESS");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
