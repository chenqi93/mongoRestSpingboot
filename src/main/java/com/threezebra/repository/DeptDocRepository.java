package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.DeptDocPrivilege;

public interface DeptDocRepository  extends  MongoRepository<DeptDocPrivilege,String>{

	DeptDocPrivilege findById(long id);

	DeptDocPrivilege findByNameContainingIgnoreCase(String deptdoc);

}
