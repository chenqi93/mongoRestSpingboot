package com.threezebra.service;

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
	
	
	public UserType save(String usertype, List<Unit> unitlst) {
		UserType userType = new UserType();
		userType.setId(System.nanoTime());
		userType.setUnit(unitlst);
		userType.setCheckFlag("TRUE");
		userType.setName(usertype);
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
	public UserType update(UserType userType, List<Unit> unitlst) {
		List<Unit> unitlist=userType.getUnit();
		for(Unit unitobj:unitlst) {
			boolean flag=containsName(unitlist, unitobj.getName());
			if(flag==false) {
				unitlist.add(unitobj);
				userType.setUnit(unitlst);
		    }
		   }
		userType.setCheckFlag("TRUE");
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

}
