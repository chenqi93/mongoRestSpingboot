package com.threezebra.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.configuration.ApplicationConfigurationProperties;
import com.threezebra.domain.JobRole;
import com.threezebra.domain.Unit;
import com.threezebra.domain.UserType;
import com.threezebra.repository.UserTypeRepository;

@Service("UserTypeService")
public class UserTypeService {
	@Autowired
	private ApplicationConfigurationProperties configurationProperties;
	@Autowired
	private UserTypeRepository userTypeRepository;
	
	
	public UserType save(String usertype,String userTypeDesc, List<Unit> unitlst) {
		UserType userType = new UserType();
		userType.setId(System.nanoTime());
		userType.setUnit(unitlst);
		userType.setCheckFlag("TRUE");
		userType.setName(usertype);
		userType.setDesc(userTypeDesc);
		userTypeRepository.save(userType);
		return userType;
	}
	
	public UserType saveXternal(String usertype, List<Unit> unitlst, List<JobRole> jobRoleList) {
		UserType userType = new UserType();
		userType.setId(System.nanoTime());
		userType.setUnit(unitlst);
		userType.setName(usertype);
		userType.setCheckFlag("TRUE");
		userType.setJobRoleList(jobRoleList);
		userTypeRepository.save(userType);
		return userType;
	}

	public List<UserType> findAll() {
		return userTypeRepository.findAll();
	}

	public void deleteUserType(UserType userType) {
		userTypeRepository.delete(userType);
	}
	public boolean containsName(final List<Unit> unitlist, final String name){
	    return unitlist.stream().filter(o -> o.getName().equals(name)).findFirst().isPresent();
	}
	public UserType addUnit(UserType userType, Unit unit) {
		List<Unit> unitlist=userType.getUnit();
	    boolean flag=containsName(unitlist, unit.getName());
			if(flag==false) {
				unitlist.add(unit);
				userType.setUnit(unitlist);
		    }
		 userTypeRepository.save(userType);
		return userType;
	}
	public UserType removeUnit(UserType userType, Unit unit) {
		List<Unit> unitlist=userType.getUnit();
	    boolean flag=containsName(unitlist, unit.getName());
			if(flag==true) {
				Iterator<Unit> itr = unitlist.iterator();
				while (itr.hasNext()) {
					Unit unitobj = (Unit) itr.next();
					if (unitobj.getName().equals(unit.getName())) {
						itr.remove();
					}
				}
				unitlist.remove(unit);
				userType.setUnit(unitlist);
		    }
		 userTypeRepository.save(userType);
		return userType;
	}

	public List<UserType> findByUnit(Unit unit) {
		return userTypeRepository.findByUnit(unit);
	}

	public void delete(UserType usertype) {
		userTypeRepository.delete(usertype);
	}

	public UserType findByUserType(String userType) {
		return userTypeRepository.findByNameContainingIgnoreCase(userType);
	}

	public UserType findById(long userType) {
		return userTypeRepository.findById(userType);
	}

	

	public UserType remupdate(UserType userType, List<Unit> untlst) {
		userType.setUnit(untlst);
		userType.setCheckFlag("FALSE");
		userTypeRepository.save(userType);
		return userType;
		
	}

	public UserType updateJobRole(UserType userType) {
		return	userTypeRepository.save(userType);
		
	}

	public UserType updateFlag(UserType userType, String flag) {
		userType.setCheckFlag(flag);
		return	userTypeRepository.save(userType);
	}

}
