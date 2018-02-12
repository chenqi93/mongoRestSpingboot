package com.threezebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.domain.Department;
import com.threezebra.domain.JobTitle;
import com.threezebra.repository.JobTitleRepository;
@Service
public class JobTitleService {
	
	@Autowired
	JobTitleRepository  jobTitleRepository;
	
	public JobTitle save(String name,Department department) {
		JobTitle jobTitle=jobTitleRepository.findByName(name);
		if(jobTitle==null ||!(jobTitle.getDepartment().getName().equals(department.getName()))) {
	        jobTitle = new JobTitle();
			jobTitle.setId(System.nanoTime());
			jobTitle.setName(name);
			jobTitle.setDepartment(department);
			jobTitle.setDepartmentName(department.getName());
		jobTitleRepository.save(jobTitle);
		}
		return jobTitle;
	}

	public List<JobTitle> findByDepartment(Department department) {
		return jobTitleRepository.findByDepartmentName(department.getName());
	}

	public JobTitle findByName(String name) {
		return jobTitleRepository.findByName(name);
	}

	public JobTitle findById(long id) {
		// TODO Auto-generated method stub
		return jobTitleRepository.findById(id);
	}
	

}
