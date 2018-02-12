package com.threezebra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.EmployeeScreen;

public interface EmployeeScreenRepository extends MongoRepository<EmployeeScreen,String> {
   EmployeeScreen findByName(String name);





}
