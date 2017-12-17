package com.threezebra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.domain.Application;
import com.threezebra.repository.ApplicationRepository;
@Service
public class ApplicationService {
	 
	@Autowired
	private ApplicationRepository applicationRepository;
	
	
	public Application save(Application application) {
		return applicationRepository.save(application);
	}
	
	public List<Application> findAll(){
		return applicationRepository.findAll();
	}

	public Application findById(long applid) {
		return applicationRepository.findById(applid);
	}
	

}
