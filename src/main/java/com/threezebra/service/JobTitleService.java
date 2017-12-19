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
		if(null!=jobTitle) {
			jobTitle.setId(System.nanoTime());
			jobTitle.setName(name);
			jobTitle.setDepartment(department);
			jobTitleRepository.save(jobTitle);
			return jobTitle;
		}
		else
		{
			JobTitle jobTitleobj = new JobTitle();
			jobTitleobj.setId(System.nanoTime());
			jobTitleobj.setName(name);
			jobTitleobj.setDepartment(department);
			jobTitleRepository.save(jobTitleobj);
			return	jobTitleobj;
		}
	
	}

	public List<JobTitle> findByDepartment(Department department) {
		return jobTitleRepository.findByDepartment(department);
	}

	public JobTitle findByName(String name) {
		return jobTitleRepository.findByName(name);
	}

	public JobTitle findById(long id) {
		// TODO Auto-generated method stub
		return jobTitleRepository.findById(id);
	}
	

}
