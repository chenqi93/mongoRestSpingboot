package com.threezebra.onesimple.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.threezebra.domain.Application;
import com.threezebra.domain.BaseLocation;
import com.threezebra.domain.BaseResponse;
import com.threezebra.domain.CallSheet;
import com.threezebra.domain.DailyDistributionGroup;
import com.threezebra.domain.Department;
import com.threezebra.domain.DistributionGroup;
import com.threezebra.domain.EmpDetail;
import com.threezebra.domain.JobRole;
import com.threezebra.domain.SpecialRole;
import com.threezebra.domain.Unit;
import com.threezebra.domain.UserType;
import com.threezebra.domain.XternalDistributionGroup;
import com.threezebra.onesimple.dto.DistributionGroupMappingJson;
import com.threezebra.onesimple.dto.XternalListJson;
import com.threezebra.service.ApplicationService;
import com.threezebra.service.BaseLocationService;
import com.threezebra.service.CallSheetService;
import com.threezebra.service.DailyDistributionGroupService;
import com.threezebra.service.DepartmentService;
import com.threezebra.service.DistributionGroupService;
import com.threezebra.service.EmployeeService;
import com.threezebra.service.JobRoleService;
import com.threezebra.service.JobTitleService;
import com.threezebra.service.SpecialRoleService;
import com.threezebra.service.UnitService;
import com.threezebra.service.UserTypeService;
import com.threezebra.service.XterlDistributionGroupService;

@RestController
@RequestMapping("masterdata")
public class DistributionGroupController {
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
	XterlDistributionGroupService xterlDistributionGroupService;
	@Autowired
	DailyDistributionGroupService dailyDistributionGroupService;
	@Autowired
	CallSheetService callSheetService;
	@Autowired
	SpecialRoleService specialRoleService;
	@Autowired
	ApplicationService applicationService;
	@Autowired
	EmployeeService employeeService;

	/*@RequestMapping(value = "/createDistributionGroup", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createDistributionGroup() {
		List<BaseLocation> baselocationList = baseLocationService.findAll();
		List<Unit> unitlist = unitService.findAll();
		List<String> location = new ArrayList<>();
		List<BaseLocation> baseLocationlst = baseLocationService.findAll();
		for (BaseLocation locationobj : baseLocationlst) {
			location.add(locationobj.getName());
		}

		List<Department> departmentlist = null;
		List<UserType> usertypelist = null;
		distributionGroupService.deleteAll();

		for (Unit unit : unitlist) {
			departmentlist = departmentService.findByUnit(unit);
			for (Department department : departmentlist) {
				DistributionGroup distributionGroup = new DistributionGroup();
				distributionGroup.setId(System.nanoTime());
				distributionGroup.setLocation(location);
				distributionGroup.setUnit(unit);
				distributionGroup.setDepartment(department);
				distributionGroup.setUserType(usertypelist);
				distributionGroup.setDefaultvalue("true");
				usertypelist = userTypeService.findByUnit(unit);
				for (UserType userType : usertypelist) {
					List<JobRole> jobrolelist = userType.getJobRoleList();
					for (JobRole jobRole : jobrolelist) {
						if (!((jobRole.getName().equals("Daily")) || (department.getName().equals("Xternal")))) {

							distributionGroup.setJobRole(userType.getJobRoleList());
							StringBuilder distributionGroupname = new StringBuilder();
							distributionGroupname.append("Distro-").append(department.getName()).append("-Dept-")
									.append(unit.getName()).append(".").append(baselocationList.get(0).getName());
							System.out.println("%%%%%%%%%%%%%%" + distributionGroupname + "." + "%%%%%%%%%%%%%%");
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

	@RequestMapping(value = "/createDailyDistributionGroup", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createDailyDistributionGroup() {
		List<BaseLocation> baselocationList = baseLocationService.findAll();
		List<Unit> unitlist = unitService.findAll();
		List<String> location = new ArrayList<>();
		List<BaseLocation> baseLocationlst = baseLocationService.findAll();
		for (BaseLocation locationobj : baseLocationlst) {
			location.add(locationobj.getName());
		}

		List<Department> departmentlist = null;
		List<UserType> usertypelist = null;

		dailyDistributionGroupService.deleteAll();
		for (Unit unit : unitlist) {
			departmentlist = departmentService.findByUnit(unit);
			for (Department department : departmentlist) {
				DailyDistributionGroup dailyDistributionGroup = new DailyDistributionGroup();
				dailyDistributionGroup.setLocation(location);
				dailyDistributionGroup.setUnit(unit);
				dailyDistributionGroup.setId(System.nanoTime());
				dailyDistributionGroup.setDepartment(department);
				dailyDistributionGroup.setUserType(usertypelist);
				dailyDistributionGroup.setDefaultvalue("true");
				for (UserType userType : usertypelist) {
					List<JobRole> jobrolelist = userType.getJobRoleList();
					for (JobRole jobRole : jobrolelist) {
						if (((jobRole.getName().equals("Daily")) || (department.getName().equals("Xternal")))) {
							dailyDistributionGroup.setJobRole(userType.getJobRoleList());
							StringBuilder distributionGroupname = new StringBuilder();
							distributionGroupname.append("Distro-").append(department.getName()).append("-DAILYHIRES-")
									.append(unit.getName()).append(".").append(baselocationList.get(0).getName());
							System.out.println("%%%%%%%%%%%%%%" + distributionGroupname + "." + "%%%%%%%%%%%%%%");
							dailyDistributionGroup.setName(distributionGroupname.toString());
							dailyDistributionGroupService.save(dailyDistributionGroup);
						}
					}
				}
			}
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
		List<UserType> usertypelist = null;

		dailyDistributionGroupService.deleteAll();
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
				for (UserType userType : usertypelist) {
					List<JobRole> jobrolelist = userType.getJobRoleList();
					for (JobRole jobRole : jobrolelist) {
						if (((jobRole.getName().equals("Daily")) || (department.getName().equals("Xternal")))) {
							callSheet.setJobRole(userType.getJobRoleList());
							StringBuilder distributionGroupname = new StringBuilder();
							distributionGroupname.append("Distro-CallSheet").append(department.getName()).append("")
									.append(unit.getName()).append(".").append(baselocationList.get(0).getName());
							System.out.println("%%%%%%%%%%%%%%" + distributionGroupname + "." + "%%%%%%%%%%%%%%");
							callSheet.setName(distributionGroupname.toString());
							callSheetService.save(callSheet);
						}
					}
				}
			}
		}

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
				response = new BaseResponse("100", "Department already exists");

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
		Unit unit =null; //unitService.findbyName(xunit);
		if (null == unit) {
			unit = new Unit();
			unit.setId(System.nanoTime());
			unit.setName(xunit);
			unitService.save(unit);
			unitlist.add(unit);
		} else {
			unitlist.add(unit);
		}
		Department department = departmentService.findbyName(xdepartment);
		if (null != department) {
			department = departmentService.update(department, xdepartment, xunit);
		} else {
			department = new Department();
			department.setId(System.nanoTime());
			department.setName(xdepartment);
			department.setUnit(unitlist);
			department = departmentService.save(xdepartment, unitlist);
		}

		UserType userType = userTypeService.findByUserType(xuserType);
		JobRole jobRole = jobRoleService.save(xjobRole);
		jobroleList.add(jobRole);
		if (null != userType) {
			System.out.println("jobrole::::size:::" + jobroleList.get(0).getName());
			userType = userTypeService.updateJobRole(userType, jobroleList);
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
	}*/
}
