package com.threezebra.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.threezebra.domain.BaseLocation;
import com.threezebra.domain.Department;
import com.threezebra.domain.DistributionGroup;
import com.threezebra.domain.JobRole;
import com.threezebra.domain.Unit;
import com.threezebra.domain.UserType;
import com.threezebra.repository.BaseLocationRepository;
import com.threezebra.repository.DistributionGroupRepository;

@Service
public class DistributionGroupService {
	Logger log = LoggerFactory.getLogger(DistributionGroupService.class);
	@Autowired
	DistributionGroupRepository distributionGroupRepository;

	@Autowired
	UnitService unitService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	UserTypeService userTypeService;
	@Autowired
	JobRoleService jobRoleService;
	@Autowired
	BaseLocationRepository baseLocationRepository;
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
	@Value("${mapping.message}")
	private String mappingmessage;
	@Value("${prefix.userType}")
	private String userTypeflag;
	public DistributionGroup save(DistributionGroup distributionGroup) {
		return distributionGroupRepository.save(distributionGroup);
	}

	public List<DistributionGroup> findAll() {
		return distributionGroupRepository.findAll();
	}

	public void usertypelist() {
		distributionGroupRepository.deleteAll();
	}

	public DistributionGroup findByName(String distributionGroup) {
		return distributionGroupRepository.findByName(distributionGroup);
	}

	public void deleteAll() {
		distributionGroupRepository.deleteAll();

	}

	public DistributionGroup createDistributionGroup(String defaultValue,List<String> location, String distributionGroup, List<Unit> unitlist,
			List<Department> departmentlst, List<UserType> userTypelst, List<JobRole> jobRolelst, String employeeListflag,String isActive) {
		DistributionGroup distributionGroupObj = distributionGroupRepository.findByName(distributionGroup);
		if (null != distributionGroupObj) {
			distributionGroupObj.setDefaultvalue(defaultValue);
			distributionGroupObj.setLocation(location);
			distributionGroupObj.setName(distributionGroup);
			distributionGroupObj.setUnitlist(unitlist);
			distributionGroupObj.setDeptlist(departmentlst);
			distributionGroupObj.setUserType(userTypelst);
			distributionGroupObj.setEmployeeListflag(employeeListflag);
			distributionGroupObj.setJobRole(jobRolelst);
			distributionGroupObj.setDistroActive(isActive);
		} else {
			distributionGroupObj = new DistributionGroup();
			distributionGroupObj.setId(System.nanoTime());
			distributionGroupObj.setLocation(location);
			distributionGroupObj.setDefaultvalue(defaultValue);
			distributionGroupObj.setName(distributionGroup);
			distributionGroupObj.setUnitlist(unitlist);
			distributionGroupObj.setDeptlist(departmentlst);
			distributionGroupObj.setEmployeeListflag(employeeListflag);
			distributionGroupObj.setUserType(userTypelst);
			distributionGroupObj.setJobRole(jobRolelst);
			distributionGroupObj.setDistroActive(isActive);
		}

		return distributionGroupRepository.save(distributionGroupObj);
	}
	public void updateDistributionGroup(Unit unit, List<Department> departmentlist, List<UserType> usertypeList,
			List<JobRole> jobRoleList) {
		for (Department department : departmentlist) {
			if (!(department.getName().equals("Xternal"))) {
				    List<BaseLocation> location=baseLocationRepository.findAll();
				   List<String> bloc=new ArrayList<>();
				    bloc.add(location.get(0).getName());
					DistributionGroup distributionGroup = new DistributionGroup();
					distributionGroup.setId(System.nanoTime());
					distributionGroup.setLocation(bloc);
					distributionGroup.setUnit(unit);
					distributionGroup.setDistroActive("1");
					distributionGroup.setEmployeeListflag("FALSE");
					distributionGroup.setUserType(usertypeList);
					;
					distributionGroup.setDepartment(department);
				
					Iterator<JobRole> itr = jobRoleList.iterator();
					while (itr.hasNext()) {
						JobRole jobRoleobj = (JobRole) itr.next();
						if (jobRoleobj.getName().equals("Daily")) {
							itr.remove();
						}
					}
					distributionGroup.setJobRole(jobRoleList);
					StringBuilder distributionGroupname = new StringBuilder();
					distributionGroupname.append(distroname);
					if (deptflag.equals("TRUE")) {
						distributionGroupname.append(department.getName()).append("-Dept-");
					}
					if (unitflag.equals("TRUE")) {
						distributionGroupname.append(unit.getName()).append(".");
					}
					
					if (blocationFlag.equals("TRUE")) {
						distributionGroupname.append(location.get(0).getName());
					}
					log.info("%%%%%%%%%%%%%%" + distributionGroupname, "distro group name");
					distributionGroup.setName(distributionGroupname.toString());
					update(distributionGroup);
					}
		}
		
	}
	public void update(DistributionGroup distributionGroup) {
		// TODO Auto-generated method stub
		
	}
}
