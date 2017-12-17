package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.EmpDetail;

public interface EmployeeRepository extends MongoRepository<EmpDetail, String> {
    EmpDetail findById(long id);

        
}
