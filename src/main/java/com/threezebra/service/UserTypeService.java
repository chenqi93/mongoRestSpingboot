package com.threezebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.configuration.ApplicationConfigurationProperties;
import com.threezebra.domain.Unit;
import com.threezebra.domain.UserType;
import com.threezebra.repository.UserTypeRepository;

@Service("UserTypeService")
public class UserTypeService {
	@Autowired
    private ApplicationConfigurationProperties configurationProperties;
   @Autowired 
    private UserTypeRepository userTypeRepository;
    
    public UserType save(String usertype,List<Unit> unitlst) {
    	UserType userType1=new UserType();
		userType1.setId(System.nanoTime());
		userType1.setUnit(unitlst);
		userType1.setName(usertype);
		userTypeRepository.save(userType1);
     return userType1;
    }

    public List<UserType> findAll() {
        return userTypeRepository.findAll();
    }
    
	public void deleteUserType(UserType userType) {
		userTypeRepository.delete(userType);
	}
    
    public UserType update(UserType userType,Unit unit) {
    		List<Unit> untlst=userType.getUnit();
    	     untlst.add(unit);
    	     userType.setUnit(untlst);  
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



	
	
	
}
