package com.threezebra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.threezebra.domain.DistributionGroupMapping;
import com.threezebra.repository.GroupAssignmentRepository;
@Service
public class DistributionGroupAssignmentService {
	  @Autowired 
	    private GroupAssignmentRepository groupAssignmentRepository;

	public  DistributionGroupMapping save(DistributionGroupMapping assignmentsVO) {
		return groupAssignmentRepository.save(assignmentsVO);
		
	}

}
