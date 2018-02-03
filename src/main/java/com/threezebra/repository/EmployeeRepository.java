package com.threezebra.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.threezebra.domain.AdditionalLocation;
import com.threezebra.domain.BaseLocation;
import com.threezebra.domain.Department;
import com.threezebra.domain.EmpDetail;
import com.threezebra.domain.JobRole;

public interface EmployeeRepository extends MongoRepository<EmpDetail, String> {
    EmpDetail findById(long id);
    List<EmpDetail> findByBaseLocation(BaseLocation baseLocation);
    @Query("{'personalPhoneNum' : ?0,'personalEmail' : ?1}")    
    List<EmpDetail> findDuplicateEmployee(String phoneNum, String email);
	List<EmpDetail> findByAdditionalLocation(AdditionalLocation additionalLocation);
	//List<EmpDetail> findByDepartment(Department department);
	List<EmpDetail> findByJobRole(JobRole jobRole);
}
