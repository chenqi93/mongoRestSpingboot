package com.threezebra.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.threezebra.domain.Department;
import com.threezebra.domain.EmpDetail;
import com.threezebra.domain.Unit;

public interface DepartmentRepository extends MongoRepository<Department, String>{
	List<Department> findByUnit(Unit unit);	
	Department findById(long id);	
	Department findByName(String name);	
}
