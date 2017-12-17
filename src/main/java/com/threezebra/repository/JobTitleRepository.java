package com.threezebra.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.threezebra.domain.Department;
import com.threezebra.domain.JobTitle;

public interface JobTitleRepository   extends MongoRepository<JobTitle,String>{
     JobTitle findByName(String name);
     List<JobTitle> findByDepartment(Department department);

}